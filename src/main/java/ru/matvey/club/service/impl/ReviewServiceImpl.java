package ru.matvey.club.service.impl;

import lombok.RequiredArgsConstructor;
import org.flywaydb.core.internal.util.StringUtils;
import org.springframework.stereotype.Service;
import org.flywaydb.core.internal.util.CollectionsUtils;
import ru.matvey.club.entity.Book;
import ru.matvey.club.entity.Review;
import ru.matvey.club.exception.exceptions.EmptyFieldsException;
import ru.matvey.club.exception.exceptions.NotFoundException;
import ru.matvey.club.repo.BookRepo;
import ru.matvey.club.repo.ReviewRepo;
import ru.matvey.club.rest.dto.BookDto;
import ru.matvey.club.rest.dto.EditReviewRequest;
import ru.matvey.club.rest.dto.NewReviewRequest;
import ru.matvey.club.rest.dto.ReviewDto;
import ru.matvey.club.service.BookService;
import ru.matvey.club.service.ReviewService;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

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
        Optional<Review> reviewFromDb = reviewRepo.findById(id);

        if (reviewFromDb.isEmpty()){
            throw new NotFoundException("Рецензия с id " + id + " не найдена");
        }

        Review review = reviewFromDb.get();

        return buildDto(review);
    }

    @Override
    public Long addNewReview(NewReviewRequest request) {

        if(!StringUtils.hasText(request.getReviewText())){
            throw new EmptyFieldsException("Некорректное поле в запросе");
        }

        Review review = new Review();
        review.setReviewText(request.getReviewText());
        review.setLikesAmount(0);
        review.setCreatedAt(LocalDateTime.now());


        final BookService bookService = null;


        //для того, чтобы установить книгу, нужно сначала создать BookService
        Long bookId = request.getBookId();
        BookDto bookDto = bookService.findByid(bookId);

        Book book = new Book();
        book.setName(bookDto.getName());
        book.setThumbnailUrl(bookDto.getThumbnailUrl());
        book.setDateOfWriting(bookDto.getDateOfWritting());

        review.setBook(book);

        reviewRepo.saveAndFlush(review);

        return review.getId();
    }

    @Override
    public void deleteById(Long id) {
        if(!reviewRepo.existsById(id)){
            throw new NotFoundException("Рецензия с id " + id + " не найдена");
        }
        reviewRepo.deleteById(id);
        return;
    }

    @Override
    public ReviewDto edit(Long id, EditReviewRequest request) {
        Optional<Review> reviewFromDb = reviewRepo.findById(id);

        if(!StringUtils.hasText(request.getEditedReviewText())){
            throw new EmptyFieldsException("Некорректное поле в запросе");
        }

        Review review = reviewFromDb.get();
        review.setReviewText(request.getEditedReviewText());
        review.setUpdatedAt(LocalDateTime.now());

        reviewRepo.save(review);

        return buildDto(review);
    }

    private ReviewDto buildDto(Review review) {
        return ReviewDto.builder()
                .reviewText(review.getReviewText())
                .likesAmount(review.getLikesAmount())
                .build();
    }
}
