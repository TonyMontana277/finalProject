package com.homework.finalProject.rest;

import com.homework.finalProject.Service.ReservationService;
import com.homework.finalProject.domain.Reservation;
import com.homework.finalProject.dto.ReservationDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

public class ReservationControllerTest {

    @Mock
    private ReservationService reservationService;

    @InjectMocks
    private ReservationController reservationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll() {
        // Arrange
        List<ReservationDto> expectedReservations = new ArrayList<>();
        when(reservationService.findAll()).thenReturn(expectedReservations);

        // Act
        ResponseEntity<List<ReservationDto>> response = reservationController.findAll();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedReservations, response.getBody());
    }

    @Test
    void createReservation() {
        // Arrange
        Reservation reservation = new Reservation();
        when(reservationService.createReservation(reservation)).thenReturn(reservation);

        // Act
        Reservation result = reservationController.createReservation(reservation);

        // Assert
        assertEquals(reservation, result);
    }

    @Test
    void deleteReservation_existingId_success() {
        // Arrange
        Long id = 1L;
        doNothing().when(reservationService).deleteReservation(id);

        // Act
        ResponseEntity<String> response = reservationController.deleteReservation(id);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Reservation deleted successfully", response.getBody());
    }

    @Test
    void deleteReservation_nonExistingId_notFound() {
        // Arrange
        Long id = 1L;
        String errorMessage = "Reservation not found";
        doThrow(new RuntimeException(errorMessage)).when(reservationService).deleteReservation(id);

        // Act
        ResponseEntity<String> response = reservationController.deleteReservation(id);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(errorMessage, response.getBody());
    }

    @Test
    void addVisitorToReservation() {
        // Arrange
        Long id = 1L;
        Long visitorId = 2L;
        doNothing().when(reservationService).addVisitorToReservation(id, visitorId);

        // Act
        ResponseEntity<Void> response = reservationController.addVisitorToReservation(id, visitorId);

        // Assert
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertNull(response.getBody());

    }

    @Test
    void addRoomToReservation() {
        // Arrange
        Long id = 1L;
        Long roomId = 2L;
        doNothing().when(reservationService).addRoomToReservation(id, roomId);

        // Act
        ResponseEntity<Void> response = reservationController.addRoomToReservation(id, roomId);

        // Assert
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertNull(response.getBody());

    }

    @Test
    void updateReservationDate() {
        // Arrange
        Long id = 1L;
        Reservation reservation = new Reservation();
        when(reservationService.updateReservationDate(id, reservation)).thenReturn(null);

        // Act
        ResponseEntity<ReservationDto> response = reservationController.updateReservationDate(id, reservation);

        // Assert
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertNull(response.getBody());

    }



}
