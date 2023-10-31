package com.level2.books.controller;

import com.level2.books.dto.BookRequestDto;
import com.level2.books.dto.BookResponseDto;
import com.level2.books.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BookController {

    private final BookService bookService;

    // 책 등록
    @PostMapping("/book")
    public BookResponseDto createBook(@RequestBody BookRequestDto requestDto) {
        return bookService.createBook(requestDto);
    }
    // 책 전체 조회
    @GetMapping("/books")
    public List<BookResponseDto> getBooks() {
        return bookService.getBooks();
    }

    // 특정 책 조회
    @GetMapping("/books/{id}")
    public BookResponseDto getBookById(@PathVariable Long bookId) {
        return bookService.getBookById(bookId);
    }
}
