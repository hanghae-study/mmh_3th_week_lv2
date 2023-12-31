package com.level2.books.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class RentalRequestDto {
    private Long bookId;
    private String phone;
    private LocalDateTime rentalDate;
}
