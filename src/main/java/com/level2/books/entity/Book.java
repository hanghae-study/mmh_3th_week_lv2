package com.level2.books.entity;

import com.level2.books.dto.BookRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "book")
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 데이터베이스 생성 순서대로 등록id 부여하기(다른 값들은 pk가 될수없음)

    // 내가 처음 설계했을때 생각한 pk는 제목이었으나 애매한것같아서 다른 값을 pk로 지정
    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "writer", nullable = false)
    private String writer;

    @Column(name = "company", nullable = false)
    private String company;

    @Column(name = "lang", nullable = false)
    private String lang;

    @Column(name = "createdAt", nullable = false)
    private LocalDate createdAt; // 등록일 yy-mm-dd 날짜만 등록

    @Column(name = "rented", nullable = false)
    private boolean rented; // 대출 상태를 나타내는 필드 - 초기값은 false -> 대출되면 값 바일

    public Book(BookRequestDto requestDto){
        this.title = requestDto.getTitle();
        this.writer = requestDto.getWriter();
        this.lang = requestDto.getLang();
        this.createdAt = LocalDate.now();
    }

}
