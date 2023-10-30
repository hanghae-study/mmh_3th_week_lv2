package com.level2.books.service;

import com.level2.books.dto.BookRequestDto;
import com.level2.books.dto.BookResponseDto;
import com.level2.books.entity.Book;
import com.level2.books.exception.BookNotFoundException;
import com.level2.books.repository.BookRepository;

import java.util.List;

public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // 책 등록
    public BookResponseDto createBook(BookRequestDto requestDto) {
        Book book = new Book(requestDto);

        Book saveBook = bookRepository.save(book);

        BookResponseDto bookResponseDto = new BookResponseDto(saveBook);
        return bookResponseDto;
    }

    // 특정 책 조회
    public BookResponseDto getBookById(Long id) {
        Book book = bookRepository.findBookById(id);

        if (book == null) {
            // 조회한 책정보와 일치가 없으면 예외처리
            throw new BookNotFoundException("입력하신 정보에 해당하는 책이 없습니다.");
        }

        return new BookResponseDto(book);
    }

    // 책 전체 조회
    public List<BookResponseDto> getBooks() {
        return bookRepository.findAllByOrderByModifiedAtAsc().stream().map(BookResponseDto::new).toList();
    }

}
