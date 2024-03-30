package ru.matvey.club.service;

import ru.matvey.club.rest.dto.BookDto;
import ru.matvey.club.rest.dto.NewBookRequest;

public interface BookService {
    Long addNewBook(NewBookRequest request);

    BookDto findByid(Long id);
}
