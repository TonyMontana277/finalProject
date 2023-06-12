package com.homework.finalProject.rest;

import com.homework.finalProject.Service.ReservationService;
import com.homework.finalProject.domain.Reservation;
import com.homework.finalProject.dto.ReservationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;

    @GetMapping("/reservations")
    public ResponseEntity<List<ReservationDto>> findAll() {
        List<ReservationDto> reservations = reservationService.findAll();
        return ResponseEntity.ok(reservations);
    }




    @PostMapping("/reservations")
    public Reservation createReservation(@RequestBody Reservation reservation){
        return reservationService.createReservation(reservation);
    }

    @PostMapping("/reservations/{id}/visitors/{visitorId}")
    public ResponseEntity<Void> addVisitorToReservation(@PathVariable Long id, @PathVariable Long visitorId){
        reservationService.addVisitorToReservation(id, visitorId);

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @PostMapping("/reservations/{id}/rooms/{roomId}")
    public ResponseEntity<Void> addRoomToReservation(@PathVariable Long id, @PathVariable Long roomId){
        reservationService.addRoomToReservation(id, roomId);

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @PutMapping("/reservations/update/{id}/rooms/{roomId}")
    public ResponseEntity<ReservationDto> updateReservation(@PathVariable Long id, @PathVariable Long roomId){
        reservationService.updateRoomReservation(id, roomId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @PutMapping("/reservations/newDate/{id}")
    public ResponseEntity<ReservationDto> updateReservationDate(@PathVariable Long id, @RequestBody Reservation reservation){
        reservationService.updateReservationDate(id, reservation);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @DeleteMapping("reservations/delete/{id}")
    public ResponseEntity<String> deleteReservation(@PathVariable Long id) {
        try {
            reservationService.deleteReservation(id);
            return ResponseEntity.ok("Reservation deleted successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}