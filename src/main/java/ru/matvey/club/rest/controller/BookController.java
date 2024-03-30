package ru.matvey.club.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.matvey.club.rest.dto.BookDto;
import ru.matvey.club.rest.dto.NewBookRequest;
import ru.matvey.club.service.BookService;

@RestController
@RequestMapping("/api/v1/book")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    @PostMapping
    public ResponseEntity<Long> add(@RequestBody NewBookRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.addNewBook(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getById(@PathVariable Long id){
        return ResponseEntity.ok(bookService.findByid(id));
    }
}
