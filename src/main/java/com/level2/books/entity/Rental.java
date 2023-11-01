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

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long rentalId; // 대여 엔티티의 고유값

    @ManyToOne // 멤버:대여 = 1:N (멤버한명이 여러개 대여 가능)
    @JoinColumn(name = "bookId", nullable = false)
    private Book book; // 대여한 책에 대한 참조

    // 책:대여 = 1:N (책 하나를 여러번 대여 가능)
    // 원래를 여러권의 책이라 N:N 이 더 적합하지만 복잡성때문제 잘 사용 X
    @ManyToOne
    @JoinColumn(name = "phone", nullable = false)
    private Member member; // 멤버 엔티티 참조

    @Column(name = "rental_date")
    private LocalDateTime rentalDate; // 대여 일자

    @Column(name = "rented", nullable = false)
    private boolean rented; // 대출이 되었는가? 대출이 가능한가?

    public Rental(RentalRequestDto requestDto, Book book, Member member) {
        this.book = book;
        this.member = member;
        this.rentalDate = LocalDateTime.now();
        this.rented = false;
    }
}
