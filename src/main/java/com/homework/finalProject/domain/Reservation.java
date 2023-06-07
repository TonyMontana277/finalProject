package com.homework.finalProject.domain;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table
public class Reservation {
    @Id
    private Long id;
    @Column
    private LocalDate startDate;
    @Column
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "roomId")
    private Room room;

}
