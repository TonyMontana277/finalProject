package com.homework.finalProject.rest;

import com.homework.finalProject.Service.RoomService;
import com.homework.finalProject.domain.Room;
import com.homework.finalProject.dto.RoomDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class RoomControllerTest {

    @Mock
    private RoomService roomService;

    @InjectMocks
    private RoomController roomController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAllRooms() {
        // Arrange
        List<RoomDto> rooms = new ArrayList<>();
        rooms.add(new RoomDto());
        when(roomService.findAll()).thenReturn(rooms);

        // Act
        ResponseEntity<List<RoomDto>> response = roomController.findAllRooms();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(rooms, response.getBody());
        verify(roomService, times(1)).findAll();
    }

    @Test
    void testFindRoomById_ExistingId() {
        // Arrange
        Long roomId = 1L;
        RoomDto roomDto = new RoomDto();
        when(roomService.findRoomById(roomId)).thenReturn(Optional.of(roomDto));

        // Act
        ResponseEntity<RoomDto> response = roomController.findRoomById(roomId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(roomDto, response.getBody());
        verify(roomService, times(1)).findRoomById(roomId);
    }

    @Test
    void testFindRoomById_NonExistingId() {
        // Arrange
        Long roomId = 1L;
        when(roomService.findRoomById(roomId)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<RoomDto> response = roomController.findRoomById(roomId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(roomService, times(1)).findRoomById(roomId);
    }

    @Test
    void testGetAllAvailableRooms() {
        // Arrange
        List<Room> availableRooms = new ArrayList<>();
        availableRooms.add(new Room());
        when(roomService.findAllAvailableRooms()).thenReturn(availableRooms);

        // Act
        ResponseEntity<List<Room>> response = roomController.getAllAvailableRooms();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(availableRooms, response.getBody());
        verify(roomService, times(1)).findAllAvailableRooms();
    }

    @Test
    void testAddRoom() {
        // Arrange
        Room room = new Room();

        // Act
        roomController.addRoom(room);

        // Assert
        verify(roomService, times(1)).addRoom(room);
    }

    @Test
    void testDeleteRoom() {
        // Arrange
        Long roomId = 1L;

        // Act
        roomController.deleteRoom(roomId);

        // Assert
        verify(roomService, times(1)).deleteRoom(roomId);
    }
}
