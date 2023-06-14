package com.homework.finalProject.rest;

import com.homework.finalProject.Service.RoomService;
import com.homework.finalProject.domain.Room;
import com.homework.finalProject.dto.RoomDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;
    @GetMapping("/rooms")
    public ResponseEntity<List<RoomDto>> findAllRooms(){
        return ResponseEntity.ok(roomService.findAll());
    }
    @GetMapping("/rooms/{id}")
    public ResponseEntity<RoomDto> findRoomById(@PathVariable Long id) {
        return roomService.findRoomById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("rooms/available")
    public ResponseEntity<List<Room>> getAllAvailableRooms() {
        List<Room> availableRooms = roomService.findAllAvailableRooms();
        return new ResponseEntity<>(availableRooms, HttpStatus.OK);
    }

    @PostMapping("/rooms")
    public void addRoom(@RequestBody Room room){
        roomService.addRoom(room);
    }



//    @GetMapping("/free-rooms")
//    public void findAllFreeRooms(){
//
//    }
//    @GetMapping("/reserved-rooms")
//    public void findAllReservedRooms(){
//
//    }
//
//
    @DeleteMapping("/rooms/{id}")
    public void deleteRoom(@PathVariable Long id){
        roomService.deleteRoom(id);
    }
}
