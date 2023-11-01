package com.level2.books.service;

import com.level2.books.dto.RentalRequestDto;
import com.level2.books.dto.RentalResponseDto;
import com.level2.books.entity.Book;
import com.level2.books.entity.Member;
import com.level2.books.entity.Rental;
import com.level2.books.repository.BookRepository;
import com.level2.books.repository.MemberRepository;
import com.level2.books.repository.RentalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RentalService {

    private final RentalRepository rentalRepository;
    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;

    public RentalResponseDto createRent(RentalRequestDto requestDto) {
        String phone = requestDto.getPhone();
        Member member = memberRepository.findById(phone)
                .orElseThrow(() -> new RuntimeException("회원을 찾을 수 없습니다."));

        if (hasUnreturnedBooks(phone)) {
            throw new RuntimeException("회원이 반납하지 않은 책이 있어 대출할 수 없습니다.");
        }

        Long bookId = requestDto.getBookId();
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("책을 찾을 수 없습니다."));

        // 책의 상태를 확인하고 변경
        checkAndSetBookStatus(book, true); // true는 대출 가능 상태

        Rental rental = new Rental();
        rental.setMember(member);
        rental.setBook(book);
        rental.setRented(true);
        rental.setRentalDate(LocalDateTime.now());
        rentalRepository.save(rental);

        return new RentalResponseDto(rental);
    }

    private boolean hasUnreturnedBooks(String phone) {
        // 반납하지 않은 책이 있는지 확인하는 로직
        List<Rental> unreturnedRentals = rentalRepository.findByMemberPhoneAndRentedIsFalse(phone);
        return !unreturnedRentals.isEmpty();
    }

    private void checkAndSetBookStatus(Book book, boolean availability) {
        if (!book.isAvailable() == availability) {
            throw new RuntimeException(availability ? "선택한 책은 이미 대출 중입니다." : "선택한 책은 대출 가능하지 않습니다.");
        }
        book.setAvailable(!availability);
        bookRepository.save(book);
    }

    // 1. 회원 전화번로를 입력하면
    // 2. 해당 회원이 대출한 책의 전체 내역을 반환
    public List<RentalResponseDto> getRentByMemberId(String phone) {

        // 대출내역 조회
        List<Rental> rentals = rentalRepository.findByMemberPhoneAndRentedIsFalse(phone);

        // Dto로 매핑
        List<RentalResponseDto> rentalList = rentals.stream()
                .map(RentalResponseDto::new)
                .collect(Collectors.toList());


        // 대출일 기준으로 정렬
        rentalList.sort(Comparator.comparing(RentalResponseDto::getRentalDate));
        return rentalList;
    }

}
