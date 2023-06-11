package com.homework.finalProject.Service;

import com.homework.finalProject.domain.Reservation;
import com.homework.finalProject.domain.Room;
import com.homework.finalProject.domain.Visitor;
import com.homework.finalProject.dto.ReservationDto;
import com.homework.finalProject.repository.ReservationRepository;
import com.homework.finalProject.repository.RoomRepository;
import com.homework.finalProject.repository.VisitorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final RoomRepository roomRepository;
    private  final VisitorRepository visitorRepository;

    private static ReservationDto buildReservationDto(Reservation reservation) {
        if (reservation != null) {
            List<Long> roomIds = reservation.getRooms().stream()
                    .map(Room::getId)
                    .collect(Collectors.toList());

            return ReservationDto.builder()
                    .id(reservation.getId())
                    .visitor(reservation.getVisitor())
                    .startDate(reservation.getStartDate())
                    .endDate(reservation.getEndDate())
                    .roomIds(roomIds)
                    .build();
        } else {
            return null; // Return null for null reservations
        }
    }



    public List<ReservationDto> findAll() {
        List<Reservation> reservations = reservationRepository.findAll();

        if (reservations != null) {
            return reservations.stream()
                    .filter(Objects::nonNull)
                    .map(ReservationService::buildReservationDto)
                    .filter(Objects::nonNull) // Filter out any null reservation DTOs
                    .collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }



    public void addVisitorToReservation(Long reservationId, Long visitorId) {
        Optional<Reservation> reservationOptional = reservationRepository.findById(reservationId);
        Optional<Visitor> visitorOptional = visitorRepository.findById(visitorId);

        if (reservationOptional.isPresent() && visitorOptional.isPresent()) {
            Reservation reservation = reservationOptional.get();
            Visitor visitor = visitorOptional.get();

            reservation.setVisitor(visitor);
            reservationRepository.save(reservation);
        } else {
            throw new RuntimeException("Reservation or Visitor not found");
        }
    }

    public void addRoomToReservation(Long roomId, Long reservationId) {
        Optional<Room> roomOptional = roomRepository.findById(roomId);
        Optional<Reservation> reservationOptional = reservationRepository.findById(reservationId);

        if (roomOptional.isPresent() && reservationOptional.isPresent()) {
            Room room = roomOptional.get();
            Reservation reservation = reservationOptional.get();

            room.setReservation(reservation); // Set the reservation for the room
            room.setAvailable(false);

            roomRepository.save(room); // Save the updated room
        } else {
            // Handle the case when either the room or reservation is not found
            // You can throw an exception, log an error, or take appropriate action based on your requirements
        }
    }


    public Reservation createReservation(Reservation reservation){
        return reservationRepository.save(reservation);
    }


    public Reservation updateRoomReservation(Long id, Long roomId) {
        Optional<Reservation> optionalReservation = reservationRepository.findById(id);
        Optional<Room> roomOptional = roomRepository.findById(roomId);

        if (optionalReservation.isPresent() && roomOptional.isPresent()) {
            Reservation existingReservation = optionalReservation.get();
            Room room = roomOptional.get();

            // Add the room to the list of rooms in the reservation
            existingReservation.getRooms().add(room);

            // Set the room availability to false
            room.setAvailable(false);

            return reservationRepository.save(existingReservation);
        } else {
            throw new RuntimeException("Reservation or Room not found");
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
