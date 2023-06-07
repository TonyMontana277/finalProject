package com.homework.finalProject.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column
    private Integer capacity;
    @Column
    private Boolean availability;

    @ManyToOne
    @JoinColumn
    private Visitor visitor;

   @OneToMany(mappedBy = "room")
   private List<Reservation> reservations;



}
