package com.homework.finalProject.repository;

import com.homework.finalProject.domain.Room;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class RoomRepositoryTest {
    private RoomRepository roomRepository;

    @Test
    void testFindAllAvailableRooms() {
        // Arrange
        Room availableRoom1 = new Room();
        availableRoom1.setId(1L);
        availableRoom1.setCapacity(2);
        availableRoom1.setAvailable(true);

        Room availableRoom2 = new Room();
        availableRoom2.setId(2L);
        availableRoom2.setCapacity(4);
        availableRoom2.setAvailable(true);

        Room unavailableRoom = new Room();
        unavailableRoom.setId(3L);
        unavailableRoom.setCapacity(6);
        unavailableRoom.setAvailable(false);

        List<Room> rooms = Arrays.asList(availableRoom1, availableRoom2, unavailableRoom);

        roomRepository = Mockito.mock(RoomRepository.class);
        when(roomRepository.findAllAvailableRooms()).thenReturn(Arrays.asList(availableRoom1, availableRoom2));

        // Act
        List<Room> result = roomRepository.findAllAvailableRooms();

        // Assert
        Assertions.assertEquals(2, result.size());
        Assertions.assertTrue(result.contains(availableRoom1));
        Assertions.assertTrue(result.contains(availableRoom2));
        Assertions.assertFalse(result.contains(unavailableRoom));

        verify(roomRepository, times(1)).findAllAvailableRooms();
    }
}
