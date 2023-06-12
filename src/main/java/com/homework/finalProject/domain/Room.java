package com.homework.finalProject.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column
    private Integer capacity;
    @Column
    private Boolean available;

    @ManyToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

    public Room(Long roomId, int i, boolean b) {
    }

    public Boolean isAvailable() {
        return available;
    }

}
