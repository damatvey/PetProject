package ru.matvey.club.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.matvey.club.rest.dto.EditReviewRequest;
import ru.matvey.club.rest.dto.NewReviewRequest;
import ru.matvey.club.rest.dto.ReviewDto;
import ru.matvey.club.service.ReviewService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping
    public ResponseEntity<List<ReviewDto>> getAll(){
        return ResponseEntity.ok(reviewService.findAllReviews());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewDto> getById(@PathVariable Long id){

        return ResponseEntity.ok(reviewService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Long> add(@RequestBody NewReviewRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(reviewService.addNewReview(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        reviewService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ReviewDto> edit(@PathVariable Long id, @RequestBody EditReviewRequest request) {
        return ResponseEntity.ok(reviewService.edit(id, request));
    }
}
