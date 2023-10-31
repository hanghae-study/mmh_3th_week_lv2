package com.level2.books.entity;

import com.level2.books.dto.ReturnRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity // JPA 관리할 엔티티 클래스 지정
@Getter
@Setter
@Table(name = "return")
@NoArgsConstructor
public class Return {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long returnId; // 대여 엔티티의 고유값

    // JPA 연관관계 매핑 사용 (안쓰고는 어떻게?)
    @ManyToOne // 멤버:대여 = 1:N (멤버한명이 여러개 반납 가능)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book; // 반납한 책에 대한 참조

    // 책:반납 = 1:N (책 하나를 여러번 반납 가능)
    // 원래를 여러권의 책이라 N:N 이 더 적합하지만 복잡성때문제 잘 사용 X
    @ManyToOne
    @JoinColumn(name = "phone", nullable = false)
    private Member member; // 멤버 엔티티 참조

    @Column(name = "return_date")
    private LocalDateTime returnDate; // 반납 일자

    @Column(name = "return_status")
    private boolean returnStatus; // 반납상태 - 디폴트 false - 초기화할필요없음

    public Return(ReturnRequestDto requestDto, Book book, Member member) {
        this.book = book;
        this.member = member;
        this.returnDate = requestDto.getReturnDate();
    }
}
