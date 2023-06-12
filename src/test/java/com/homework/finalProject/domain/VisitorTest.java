package com.homework.finalProject.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class VisitorTest {

    @Test
    void testConstructorAndGetters() {
        // Arrange
        Long id = 1L;
        String name = "John Doe";
        Long passportId = 123456789L;
        List<Reservation> reservations = new ArrayList<>();

        // Act
        Visitor visitor = new Visitor(id, name, passportId, reservations);

        // Assert
        Assertions.assertEquals(id, visitor.getId());
        Assertions.assertEquals(name, visitor.getName());
        Assertions.assertEquals(passportId, visitor.getPassportId());
        Assertions.assertEquals(reservations, visitor.getReservations());
    }

    @Test
    void testSetters() {
        // Arrange
        Visitor visitor = new Visitor();
        Long id = 1L;
        String name = "John Doe";
        Long passportId = 123456789L;
        List<Reservation> reservations = new ArrayList<>();

        // Act
        visitor.setId(id);
        visitor.setName(name);
        visitor.setPassportId(passportId);
        visitor.setReservations(reservations);

        // Assert
        Assertions.assertEquals(id, visitor.getId());
        Assertions.assertEquals(name, visitor.getName());
        Assertions.assertEquals(passportId, visitor.getPassportId());
        Assertions.assertEquals(reservations, visitor.getReservations());
    }

    // Add more test methods as needed
}
