package com.homework.finalProject.Service;

import com.homework.finalProject.domain.Room;
import com.homework.finalProject.dto.RoomDto;
import com.homework.finalProject.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomService {

   private final RoomRepository roomRepository;

   private static RoomDto buildHostelDto(Room room){
       return RoomDto.builder()
               .id(room.getId())
               .capacity(room.getCapacity())
               .availability(room.getAvailability())
               .build();
   }

   public void createRooms(){
      for (int i = 0; i<30; i++){
         Room room = new Room();
         room.setAvailability(true);
         room.setCapacity(2);
         roomRepository.save(room);
      }
      for (int i = 0; i<30; i++){
         Room room = new Room();
         room.setAvailability(true);
         room.setCapacity(4);
         roomRepository.save(room);
      }
      for (int i = 0; i<30; i++){
         Room room = new Room();
         room.setAvailability(true);
         room.setCapacity(6);
         roomRepository.save(room);
      }
   }

   public List<RoomDto> findAll(){
      return  roomRepository.findAll()
              .stream()
              .map(RoomService::buildHostelDto)
              .collect(Collectors.toList());
   }

   public Optional<RoomDto> findRoomById(Long id){
      return roomRepository.findById(id).map(RoomService::buildHostelDto);
   }
}
