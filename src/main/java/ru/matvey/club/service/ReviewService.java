package ru.matvey.club.service;

import ru.matvey.club.rest.dto.EditReviewRequest;
import ru.matvey.club.rest.dto.NewReviewRequest;
import ru.matvey.club.rest.dto.ReviewDto;

import java.util.List;

public interface ReviewService {
    List<ReviewDto> findAllReviews();

    ReviewDto findById(Long id);

    Long addNewReview(NewReviewRequest request);

    void deleteById(Long id);

    ReviewDto edit(Long id, EditReviewRequest request);
}