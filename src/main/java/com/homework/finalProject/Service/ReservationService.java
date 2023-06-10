package com.homework.finalProject.Service;

import com.homework.finalProject.domain.Reservation;
import com.homework.finalProject.domain.Room;
import com.homework.finalProject.dto.ReservationDto;
import com.homework.finalProject.repository.ReservationRepository;
import com.homework.finalProject.repository.RoomRepository;
import com.homework.finalProject.repository.VisitorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final RoomRepository roomRepository;
    private  final VisitorRepository visitorRepository;

    private static ReservationDto buildReservationDto(Reservation reservation){
        return ReservationDto.builder()
                .room(reservation.getRoom())
                .id(reservation.getId())
                .visitor(reservation.getVisitor())
                .startDate(reservation.getStartDate())
                .endDate(reservation.getEndDate())
                .build();
    }

    public List<ReservationDto> findAll(){
        return reservationRepository.findAll().stream()
                .map(ReservationService::buildReservationDto)
                .collect(Collectors.toList());
    }

    public void addVisitorToReservation(Long visitorId, Long reservationId){
        var visitor = visitorRepository.findById(visitorId).get();
        var reservation = reservationRepository.findById(reservationId).get();
        reservation.setVisitor(visitor);

        reservationRepository.save(reservation);
    }

    public void addRoomToReservation(Long roomId, Long reservationId){
        var room = roomRepository.findById(roomId).get();
        var reservation = reservationRepository.findById(reservationId).get();
        reservation.setRoom(room);
        room.setAvailable(false);
        System.out.println("success1");
        reservationRepository.save(reservation);
        System.out.println("success2");
    }

    public Reservation createReservation(Reservation reservation){
        return reservationRepository.save(reservation);
    }


    public ReservationDto updateRoomReservation(Long id, Long roomId){
        //look up reservation and room in db
        Optional<Reservation> optionalReservation = reservationRepository.findById(id);
        Optional<Room> room = roomRepository.findById(roomId);
        if (optionalReservation.isPresent() && room.isPresent()){
            Reservation existingReservation = optionalReservation.get();
            existingReservation.setRoom(room.get());
            // Change the availability of the room to false
            Room updatedRoom = room.get();
            updatedRoom.setAvailable(false);
            Reservation reservation = reservationRepository.save(existingReservation);
            return buildReservationDto(reservation);
        }else {
            try {
                throw new FileNotFoundException();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public ReservationDto updateReservationDate(Long id, Reservation reservation){
        Optional<Reservation> optionalReservation = reservationRepository.findById(id);
        if (optionalReservation.isPresent()){
            Reservation existingReservation = optionalReservation.get();
            existingReservation.setStartDate(reservation.getStartDate());
            existingReservation.setEndDate(reservation.getEndDate());
            Reservation reservationToBuildDto = reservationRepository.save(existingReservation);
            return buildReservationDto(reservationToBuildDto);
        }else {
            try {
                throw new FileNotFoundException();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

        }
    }

}
