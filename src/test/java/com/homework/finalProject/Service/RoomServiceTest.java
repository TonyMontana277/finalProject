package com.homework.finalProject.Service;

import com.homework.finalProject.domain.Room;
import com.homework.finalProject.dto.RoomDto;
import com.homework.finalProject.repository.RoomRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class RoomServiceTest {

    private RoomService roomService;

    @Mock
    private RoomRepository roomRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        roomService = new RoomService(roomRepository);
    }

    @Test
    void testFindAll() {
        // Arrange
        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room(1L, 2, true));
        rooms.add(new Room(2L, 4, true));

        when(roomRepository.findAll()).thenReturn(rooms);

        // Act
        List<RoomDto> roomDtos = roomService.findAll();

        // Assert
        assertEquals(rooms.size(), roomDtos.size());
        for (int i = 0; i < rooms.size(); i++) {
            Room room = rooms.get(i);
            RoomDto roomDto = roomDtos.get(i);
            assertEquals(room.getId(), roomDto.getId());
            assertEquals(room.getCapacity(), roomDto.getCapacity());
            assertEquals(room.getAvailable(), roomDto.getAvailability());
        }

        verify(roomRepository, times(1)).findAll();
    }

    @Test
    void testFindRoomById() {
        // Arrange
        Long roomId = 1L;
        Room room = new Room(roomId, 2, true);

        when(roomRepository.findById(roomId)).thenReturn(Optional.of(room));

        // Act
        Optional<RoomDto> roomDtoOptional = roomService.findRoomById(roomId);

        // Assert
        Assertions.assertTrue(roomDtoOptional.isPresent());
        RoomDto roomDto = roomDtoOptional.get();
        assertEquals(room.getId(), roomDto.getId());
        assertEquals(room.getCapacity(), roomDto.getCapacity());
        assertEquals(room.getAvailable(), roomDto.getAvailability());

        verify(roomRepository, times(1)).findById(roomId);
    }

    @Test
    void testUpdateRoom() {
        // Arrange
        Long roomId = 1L;
        Room existingRoom = new Room();
        existingRoom.setId(roomId);
        existingRoom.setCapacity(2);
        existingRoom.setAvailable(true);

        RoomDto updatedRoomDto = RoomDto.builder()
                .id(roomId)
                .capacity(4)
                .availability(false)
                .build();

        when(roomRepository.findById(roomId)).thenReturn(Optional.of(existingRoom));
        when(roomRepository.save(existingRoom)).thenReturn(existingRoom);

        // Act
        RoomDto resultDto = roomService.updateRoom(roomId, updatedRoomDto);

        // Assert
        assertNotNull(resultDto);
        assertEquals(existingRoom.getId(), resultDto.getId());
        assertEquals(updatedRoomDto.getCapacity(), resultDto.getCapacity());
        assertEquals(updatedRoomDto.getAvailability(), resultDto.getAvailability());

        verify(roomRepository, times(1)).findById(roomId);
        verify(roomRepository, times(1)).save(existingRoom);
    }
    @Test
    void testAddRoom() {
        // Arrange
        Room room = new Room();
        room.setCapacity(2);
        room.setAvailable(true);

        Room savedRoom = new Room();
        savedRoom.setId(1L);
        savedRoom.setCapacity(room.getCapacity());
        savedRoom.setAvailable(room.getAvailable());

        when(roomRepository.save(room)).thenReturn(savedRoom);

        // Act
        Room result = roomService.addRoom(room);

        // Assert
        assertNotNull(result);
        assertEquals(savedRoom.getId(), result.getId());
        assertEquals(savedRoom.getCapacity(), result.getCapacity());
        assertEquals(savedRoom.getAvailable(), result.getAvailable());

        verify(roomRepository, times(1)).save(room);
    }

    @Test
    void testDeleteRoom() {
        // Arrange
        Long roomId = 1L;
        Room room = new Room();
        room.setId(roomId);

        when(roomRepository.findById(roomId)).thenReturn(Optional.of(room));

        // Act
        roomService.deleteRoom(roomId);

        // Assert
        verify(roomRepository, times(1)).findById(roomId);
        verify(roomRepository, times(1)).delete(room);
    }


    @Test
    void testFindAllAvailableRooms() {
        // Arrange
        List<Room> availableRooms = new ArrayList<>();
        availableRooms.add(new Room(1L, 2, true));
        availableRooms.add(new Room(2L, 4, true));

        when(roomRepository.findAllAvailableRooms()).thenReturn(availableRooms);

        // Act
        List<Room> result = roomService.findAllAvailableRooms();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(availableRooms, result);

        verify(roomRepository, times(1)).findAllAvailableRooms();
    }



}
