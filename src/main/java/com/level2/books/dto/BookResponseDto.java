package com.level2.books.dto;

import com.level2.books.entity.Book;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class BookResponseDto {
    private Long id;
    private String title;
    private String writer;
    private String company;
    private String lang;
    private LocalDate createdAt;

    public BookResponseDto(Book book) {
        this.id = book.getId();
        this.title = book.getTitle(); // 조회리스트에 가져와야하는 값
        this.company = book.getCompany();
        this.writer = book.getWriter(); // 조회리스트에 가져와야하는 값
        this.lang = book.getLang();
        this.createdAt = book.getCreatedAt();

    }
}