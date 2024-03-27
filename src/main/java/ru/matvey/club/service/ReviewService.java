package ru.matvey.club.service;

import ru.matvey.club.rest.dto.ReviewDto;

import java.util.List;

public interface ReviewService {
    List<ReviewDto> findAllReviews();

    ReviewDto findById(Long id);
}