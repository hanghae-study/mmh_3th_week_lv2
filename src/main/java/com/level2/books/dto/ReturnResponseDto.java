package com.level2.books.dto;

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

    // 책의 고유값 + 필요정보
    private Long bookId;
    private String writer;
    private String title;

    // 멤버 필드 값 + 필요한 정보
    private String name;
    private String phone;

    // 리턴 고유값 + 필요정보
    private LocalDateTime returnDate;

    // 성공여부, 메세지
    private boolean success;
    private String message;


    public ReturnResponseDto(Return aReturn) {
        this.bookId = getBookId(); // 책의 고유값
        this.writer = getWriter(); // 작가
        this.title = getTitle(); // 제목
        this.phone = getPhone(); // 회원의고유값
        this.name = getName(); // 회원이름
        this.returnDate = LocalDateTime.now();
        this.success = success;
        this.message = message;

    }

}
