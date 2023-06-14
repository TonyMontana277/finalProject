package com.homework.finalProject.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class ReservationTest {

    @Test
    void testConstructorAndGetters() {
        // Arrange
        Long id = 1L;
        LocalDate startDate = LocalDate.of(2023, 1, 1);
        LocalDate endDate = LocalDate.of(2023, 1, 10);
        List<Room> rooms = new ArrayList<>();
        Visitor visitor = new Visitor();

        // Act
        Reservation reservation = new Reservation(id, startDate, endDate, rooms, visitor);

        // Assert
        Assertions.assertEquals(id, reservation.getId());
        Assertions.assertEquals(startDate, reservation.getStartDate());
        Assertions.assertEquals(endDate, reservation.getEndDate());
        Assertions.assertEquals(rooms, reservation.getRooms());
        Assertions.assertEquals(visitor, reservation.getVisitor());
    }

    @Test
    void testSetters() {
        // Arrange
        Reservation reservation = new Reservation();
        Long id = 1L;
        LocalDate startDate = LocalDate.of(2023, 1, 1);
        LocalDate endDate = LocalDate.of(2023, 1, 10);
        List<Room> rooms = new ArrayList<>();
        Visitor visitor = new Visitor();

        // Act
        reservation.setId(id);
        reservation.setStartDate(startDate);
        reservation.setEndDate(endDate);
        reservation.setRooms(rooms);
        reservation.setVisitor(visitor);

        // Assert
        Assertions.assertEquals(id, reservation.getId());
        Assertions.assertEquals(startDate, reservation.getStartDate());
        Assertions.assertEquals(endDate, reservation.getEndDate());
        Assertions.assertEquals(rooms, reservation.getRooms());
        Assertions.assertEquals(visitor, reservation.getVisitor());
    }

}
