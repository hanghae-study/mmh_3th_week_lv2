package com.level2.books.dto;

import com.level2.books.entity.Book;
import com.level2.books.entity.Member;
import com.level2.books.entity.Rental;

import java.time.LocalDateTime;

public class RentalResponseDto {

    // 북의 필드 값
    private Long bookId;
    private Book write;
    private Book titie;

    // 멤버의 필드 값
    private String memberRRN;
    private String memberName;
    private String memberPhone;

    // 렌탈의 필드 값
    private Long rentalId;
    private LocalDateTime rentalDate; // 대출일
    private LocalDateTime returnDate; // 반납일

    public RentalResponseDto(Rental rental) {
        this.rentalId = rental.getRentalId();
        this.rentalDate = rental.getRentalDate();
        this.returnDate = rental.getReturnDate();
        this.bookId= rental.getBook().getId(); // 렌탈한 책의 id 가져오기
    }

}
