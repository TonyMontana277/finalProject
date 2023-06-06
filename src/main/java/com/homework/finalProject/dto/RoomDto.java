package com.homework.finalProject.dto;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoomDto {
    private Long id;
    private Integer capacity;
    private Boolean availability;

}
