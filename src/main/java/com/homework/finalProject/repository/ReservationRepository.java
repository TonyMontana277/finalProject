package com.homework.finalProject.repository;

import com.homework.finalProject.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
//    Optional<Reservation> findByRoomIdAndStartDateAfterAndEndDateBefore(Long id, LocalDate startDate, LocalDate endDate);
}

