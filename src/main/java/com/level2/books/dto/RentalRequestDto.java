package com.level2.books.dto;

import com.level2.books.entity.Book;
import com.level2.books.entity.Member;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class RentalRequestDto {
    private Book id;
    private Member RRN;
    private LocalDateTime rentalDate;
    private LocalDateTime returnDate;
}
