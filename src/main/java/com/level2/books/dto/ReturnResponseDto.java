package com.level2.books.dto;

import com.level2.books.entity.Rental;
import com.level2.books.entity.Return;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
public class ReturnResponseDto {

    // 반환될 책관련 정보
    private Long bookId;
    private String writer;
    private String title;

    // 반환될 회원관련 정보
    private String name;
    private String phone;

    // 리턴 고유값 -> 책 반환하면 찍히는 시간
    private LocalDateTime returnDate;

    // 성공여부, 메세지
    private boolean success;
    private String message;


    public ReturnResponseDto(Return aReturn) {

        // TODO null의 원인
        this.bookId = getBookId(); // 책의 고유값
        this.writer = getWriter(); // 작가
        this.title = getTitle(); // 제목
        this.phone = getPhone(); // 회원의고유값
        this.name = getName(); // 회원이름

        // 여기는 정상출력
        this.returnDate = LocalDateTime.now();
        this.success = success;
        this.message = message;

    }

}
