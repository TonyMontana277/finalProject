package com.homework.finalProject.dto;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VisitorDto {
    private Long id;
    private String name;
    private Long passportId;

}
