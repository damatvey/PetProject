package ru.matvey.club.rest.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class BookDto {
    private String name;
    private LocalDate dateOfWritting;
    private String thumbnailUrl;
}
