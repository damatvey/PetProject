package ru.matvey.club.rest.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReviewDto {
    private Integer likesAmount;
    private String reviewText;
}
