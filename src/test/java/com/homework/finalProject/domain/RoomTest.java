package com.homework.finalProject.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RoomTest {

    @Test
    void testConstructorAndGetters() {
        // Arrange
        Long id = 1L;
        Integer capacity = 2;
        Boolean available = true;
        Reservation reservation = new Reservation();

        // Act
        Room room = new Room(id, capacity, available, reservation);

        // Assert
        Assertions.assertEquals(id, room.getId());
        Assertions.assertEquals(capacity, room.getCapacity());
        Assertions.assertEquals(available, room.getAvailable());
        Assertions.assertEquals(reservation, room.getReservation());
    }

    @Test
    void testSetters() {
        // Arrange
        Room room = new Room();
        Long id = 1L;
        Integer capacity = 2;
        Boolean available = true;
        Reservation reservation = new Reservation();

        // Act
        room.setId(id);
        room.setCapacity(capacity);
        room.setAvailable(available);
        room.setReservation(reservation);

        // Assert
        Assertions.assertEquals(id, room.getId());
        Assertions.assertEquals(capacity, room.getCapacity());
        Assertions.assertEquals(available, room.getAvailable());
        Assertions.assertEquals(reservation, room.getReservation());
    }

}
