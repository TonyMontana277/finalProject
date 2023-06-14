package com.homework.finalProject.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class ReservationDto {
    private Long id;
    private Long visitor;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<Long> roomIds;
}

