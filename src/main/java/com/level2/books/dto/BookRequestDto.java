package com.level2.books.dto;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class BookRequestDto {
    private String title;
    private String writer;
    private String company;
    private String lang;
    private LocalDate createdAt;
}
