package com.level2.books.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class RentalRequestDto {
    private Long bookId;
    private String phone;
    private LocalDateTime rentalDate;
}
