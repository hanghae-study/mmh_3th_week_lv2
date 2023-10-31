package com.level2.books.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BookResponseDto {
    private Long bookId;
    private String title;
    private String writer;
    private String company;
    private String lang;
    private LocalDateTime regDate;

}