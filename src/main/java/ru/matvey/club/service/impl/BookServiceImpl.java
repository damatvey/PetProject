package ru.matvey.club.service.impl;

import lombok.RequiredArgsConstructor;
import org.flywaydb.core.internal.util.StringUtils;
import org.springframework.stereotype.Service;
import ru.matvey.club.entity.Book;
import ru.matvey.club.exception.exceptions.EmptyFieldsException;
import ru.matvey.club.exception.exceptions.NotFoundException;
import ru.matvey.club.repo.BookRepo;
import ru.matvey.club.rest.dto.BookDto;
import ru.matvey.club.rest.dto.NewBookRequest;
import ru.matvey.club.service.BookService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepo bookRepo;


    @Override
    public Long addNewBook(NewBookRequest request) {

        //для проверки поступааемых значений использую клаас StringUtils
        if (!StringUtils.hasText(request.getName())
                || !StringUtils.hasText(request.getThumbnailUrl())
                || !StringUtils.hasText(String.valueOf(request.getDateOfWritting()))){
            throw new EmptyFieldsException("Некорректные поля в запросе");
        }

        //можно добавить проверку на существующий год, типо, что нельзя положить год,
        //который больше нынешнего

        Book book = new Book();
        book.setName(request.getName());
        book.setDateOfWriting(request.getDateOfWritting());
        book.setThumbnailUrl(request.getThumbnailUrl());

        //ДЛЯ ТОГО, ЧТОБЫ ДОБАВИТЬ АВТОРА СНАЧАЛА НУЖНО СОЗДАТЬ AuthorService
        /*
        * Long authorId = request.getAuthorId();
        * Author author = authorService.findAuthorById(authorId);
        * book.setAuthor(author);
        * */


        return null;
    }

    @Override
    public BookDto findByid(Long id) {
        Optional<Book> bookFromDb = bookRepo.findById(id);
        //В каждом репозитории есть метод findById

        if(bookFromDb.isEmpty()){
            throw new NotFoundException("Такая книга не найдена");
        }

        Book book = bookFromDb.get();

        return buildDto(book);
    }

    private BookDto buildDto(Book book) {
        return BookDto.builder()
                .dateOfWritting(book.getDateOfWriting())
                .name(book.getName())
                .thumbnailUrl(book.getThumbnailUrl())
                .build();
    }
}
