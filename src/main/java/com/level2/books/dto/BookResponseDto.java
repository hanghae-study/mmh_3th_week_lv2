package com.level2.books.dto;

import com.level2.books.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class BookResponseDto {
    private Long bookId;
    private String title;
    private String writer;
    private String company;
    private String lang;
    private LocalDateTime regDate;
    private boolean available;

    public BookResponseDto(Book book) {
        this.bookId = book.getBookId();
        this.writer = book.getWriter();
        this.title = book.getTitle();
        this.company = book.getCompany();
        this.lang = book.getLang();
        this.regDate = book.getRegDate();
        this.available = book.isAvailable();
    }

}