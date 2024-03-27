package ru.matvey.club.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.matvey.club.entity.Review;
import ru.matvey.club.repo.ReviewRepo;
import ru.matvey.club.rest.dto.ReviewDto;
import ru.matvey.club.service.ReviewService;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepo reviewRepo;
    @Override
    public List<ReviewDto> findAllReviews() {
        List<Review> allReviews = reviewRepo.findAll();
       return allReviews.stream().map(review -> ReviewDto.builder()
               .reviewText(review.getReviewText())
               .likesAmount(review.getLikesAmount())
               .build())
               .toList();
    }

    @Override
    public ReviewDto findById(Long id) {
        return null;
    }
}
