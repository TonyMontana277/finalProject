package com.homework.finalProject.Service;

import com.homework.finalProject.domain.Reservation;
import com.homework.finalProject.domain.Room;
import com.homework.finalProject.domain.Visitor;
import com.homework.finalProject.dto.ReservationDto;
import com.homework.finalProject.repository.ReservationRepository;
import com.homework.finalProject.repository.RoomRepository;
import com.homework.finalProject.repository.VisitorRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReservationServiceTest {

    @Mock
    private ReservationRepository reservationRepository;
    @Mock
    private RoomRepository roomRepository;
    @Mock
    private VisitorRepository visitorRepository;

    @InjectMocks
    private ReservationService reservationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        // Create a Reservation object with a null Visitor
        Reservation reservation = new Reservation();
        reservation.setVisitor(null);

        // Call the method you are testing
        List<ReservationDto> reservations = reservationService.findAll();

        // Assert that the returned list is not null
        assertNotNull(reservations);

        // Assert that the list is empty since the Visitor is null
        assertTrue(reservations.isEmpty());
    }


    @Test
    void testAddVisitorToReservation() {
        // Arrange
        Long reservationId = 1L;
        Long visitorId = 2L;
        Reservation reservation = createReservation(reservationId, LocalDate.now(), LocalDate.now().plusDays(1));
        Visitor visitor = createVisitor(visitorId, "John Doe", 123456789L);

        when(reservationRepository.findById(reservationId)).thenReturn(Optional.of(reservation));
        when(visitorRepository.findById(visitorId)).thenReturn(Optional.of(visitor));
        when(reservationRepository.save(reservation)).thenReturn(reservation);

        // Act
        reservationService.addVisitorToReservation(reservationId, visitorId);

        // Assert
        Assertions.assertEquals(visitor, reservation.getVisitor());
        verify(reservationRepository, times(1)).findById(reservationId);
        verify(visitorRepository, times(1)).findById(visitorId);
        verify(reservationRepository, times(1)).save(reservation);
    }

    @Test
    void testAddRoomToReservation() {
        // Arrange
        Long roomId = 1L;
        Long reservationId = 2L;
        Room room = createRoom(roomId, 2, true);
        Reservation reservation = createReservation(reservationId, LocalDate.now(), LocalDate.now().plusDays(1));

        when(roomRepository.findById(roomId)).thenReturn(Optional.of(room));
        when(reservationRepository.findById(reservationId)).thenReturn(Optional.of(reservation));
        when(roomRepository.save(room)).thenReturn(room);

        // Act
        reservationService.addRoomToReservation(roomId, reservationId);

        // Assert
        Assertions.assertEquals(reservation, room.getReservation());
        Assertions.assertFalse(room.isAvailable());
        verify(roomRepository, times(1)).findById(roomId);
        verify(reservationRepository, times(1)).findById(reservationId);
        verify(roomRepository, times(1)).save(room);
    }

    @Test
    void testCreateReservation() {
        // Arrange
        Reservation reservation = createReservation(1L, LocalDate.now(), LocalDate.now().plusDays(1));
        when(reservationRepository.save(reservation)).thenReturn(reservation);

        // Act
        Reservation createdReservation = reservationService.createReservation(reservation);

        // Assert
        Assertions.assertEquals(reservation, createdReservation);
        verify(reservationRepository, times(1)).save(reservation);
    }

    @Test
    void testUpdateRoomReservation() {
        // Arrange
        Long reservationId = 1L;
        Long roomId = 2L;
        Reservation reservation = createReservation(reservationId, LocalDate.now(), LocalDate.now().plusDays(1));
        Room room = createRoom(roomId, 2, true);

        when(reservationRepository.findById(reservationId)).thenReturn(Optional.of(reservation));
        when(roomRepository.findById(roomId)).thenReturn(Optional.of(room));
        when(reservationRepository.save(reservation)).thenReturn(reservation);

        // Act
        Reservation updatedReservation = reservationService.updateRoomReservation(reservationId, roomId);

        // Assert
        Assertions.assertEquals(reservation, updatedReservation);
        assertTrue(reservation.getRooms().contains(room));
        Assertions.assertEquals(reservation, room.getReservation());
        Assertions.assertFalse(room.isAvailable());
        verify(reservationRepository, times(1)).findById(reservationId);
        verify(roomRepository, times(1)).findById(roomId);
        verify(reservationRepository, times(1)).save(reservation);
    }


    @Test
    void testDeleteReservation() {
        // Arrange
        Long reservationId = 1L;
        Reservation reservation = createReservation(reservationId, LocalDate.now(), LocalDate.now().plusDays(1));
        Room room1 = createRoom(1L, 2, false);
        Room room2 = createRoom(2L, 4, false);
        reservation.getRooms().add(room1);
        reservation.getRooms().add(room2);

        when(reservationRepository.findById(reservationId)).thenReturn(Optional.of(reservation));

        // Act
        reservationService.deleteReservation(reservationId);

        // Assert
        assertNull(room1.getReservation());
        assertNull(room2.getReservation());
        assertTrue(room1.isAvailable());
        assertTrue(room2.isAvailable());
        verify(roomRepository, times(2)).save(any(Room.class));
        verify(reservationRepository, times(1)).delete(reservation);
    }

    private Reservation createReservation(Long id, LocalDate startDate, LocalDate endDate) {
        Reservation reservation = new Reservation();
        reservation.setId(id);
        reservation.setStartDate(startDate);
        reservation.setEndDate(endDate);
        reservation.setRooms(new ArrayList<>());
        return reservation;
    }

    private Room createRoom(Long id, Integer capacity, Boolean available) {
        Room room = new Room();
        room.setId(id);
        room.setCapacity(capacity);
        room.setAvailable(available);
        room.setReservation(null);
        return room;
    }

    private Visitor createVisitor(Long id, String name, Long passportId) {
        Visitor visitor = new Visitor();
        visitor.setId(id);
        visitor.setName(name);
        visitor.setPassportId(passportId);
        visitor.setReservations(new ArrayList<>());
        return visitor;
    }
}
