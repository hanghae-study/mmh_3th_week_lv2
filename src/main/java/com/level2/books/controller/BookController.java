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

    // API 요청이 들어오면 컨트롤러에서 가장 처음 만나서 -> 리퀘스트디티오로 필요정보를 받아오고
    // 서비스 로직으로 가서 요청 작업을 처리해주고 -> 리스폰스 디티오에서 요청에 맞는 (혹은 반환에 필요한) 정보를 반환

    // 서비스 생성자 주입
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
    @GetMapping("/books/{bookId}")
    public BookResponseDto getBookById(@PathVariable Long bookId) {
        return bookService.getBookById(bookId);
    }
}
