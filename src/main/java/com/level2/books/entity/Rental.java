package com.level2.books.entity;

import com.level2.books.dto.RentalRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "rental")
@NoArgsConstructor
public class Rental {

    // TODO JPA 연관관계 매핑 애너테이션을 사용하지 않고 구현

    // 대여 엔티티를 새로 생성해서
    // 멤버엔티티의 고유값과 북 엔티티의 고유값으로 연결
    // 멤버(RRN) - 대여(id) - 북(id)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rentalId; // 대여 엔티티의 고유값

    @ManyToOne // 멤버:대여 = 1:N (멤버한명이 여러개 대여 가능)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book; // 대여한 책에 대한 참조

    // 책:대여 = 1:N (책 하나를 여러번 대여 가능)
    // 원래를 여러권의 책이라 N:N 이 더 적합하지만 복잡성때문제 잘 사용 X
    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member; // 멤버 엔티티 참조

    @Column(name = "rental_date", nullable = false)
    private LocalDateTime rentalDate; // 대여 일자

    @Column(name = "return_date")
    private LocalDateTime returnDate; // 반납 일자

    @Column(name = "return_status")
    private boolean returnStatus; // 반납상태 - 디폴트 false - 초기화할필요없음

    public Rental(RentalRequestDto requestDto, Book book, Member member) {
        this.book = book;
        this.member = member;
        this.rentalDate = requestDto.getRentalDate();
        this.returnDate = requestDto.getReturnDate();
    }
}
