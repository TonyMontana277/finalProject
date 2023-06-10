package com.homework.finalProject.Service;

import com.homework.finalProject.domain.Room;
import com.homework.finalProject.dto.RoomDto;
import com.homework.finalProject.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomService {

   private final RoomRepository roomRepository;

   private static RoomDto buildRoomDto(Room room){
       return RoomDto.builder()
               .id(room.getId())
               .capacity(room.getCapacity())
               .availability(room.getAvailable())
               .build();
   }

//   public void createRooms(){
//      for (int i = 0; i<30; i++){
//         Room room = new Room();
//         room.setAvailable(true);
//         room.setCapacity(2);
//         roomRepository.save(room);
//      }
//      for (int i = 0; i<30; i++){
//         Room room = new Room();
//         room.setAvailable(true);
//         room.setCapacity(4);
//         roomRepository.save(room);
//      }
//      for (int i = 0; i<30; i++){
//         Room room = new Room();
//         room.setAvailable(true);
//         room.setCapacity(6);
//         roomRepository.save(room);
//      }
//   }

   public List<RoomDto> findAll(){
      return  roomRepository.findAll()
              .stream()
              .map(RoomService::buildRoomDto)
              .collect(Collectors.toList());
   }

   public Optional<RoomDto> findRoomById(Long id){
      return roomRepository.findById(id).map(RoomService::buildRoomDto);
   }

   public Room addRoom(Room room){
      return roomRepository.save(room);
   }

   public RoomDto updateRoom(Long id, RoomDto roomDto){
      Optional<Room> optionalRoom = roomRepository.findById(id);
      if (optionalRoom.isPresent()){
         Room existingRoom = optionalRoom.get();
         existingRoom.setAvailable(roomDto.getAvailability());
         existingRoom.setCapacity(roomDto.getCapacity());
         roomRepository.save(existingRoom);
         return buildRoomDto(existingRoom);
      }else {
         try {
            throw new FileNotFoundException();
         } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
         }
      }
   }

   public void deleteRoom(Long id) {
      Optional<Room> optionalRoom = roomRepository.findById(id);
      if (optionalRoom.isPresent()) {
         Room room = optionalRoom.get();
         roomRepository.delete(room);
      } else {
         try {
            throw new FileNotFoundException();
         } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
         }
      }
   }
}

