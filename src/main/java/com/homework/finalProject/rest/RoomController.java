package com.homework.finalProject.rest;

import com.homework.finalProject.Service.RoomService;
import com.homework.finalProject.dto.RoomDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RoomController {
    private RoomService roomService;
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

    @PostMapping("/create")
    public ResponseEntity<Void> create(){
        roomService.createRooms();
        return ResponseEntity.status(HttpStatus.CREATED).build();
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
//    @PostMapping("/rooms")
//    public void addRoom(){
//
//    }
//
//    @DeleteMapping("/rooms")
//    public void deleteRoom(){
//
//    }
}
