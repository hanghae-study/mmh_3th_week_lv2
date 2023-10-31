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

        // 0. 회원의 고유값을 받아오기
        String phone = requestDto.getPhone(); // 전화번호를 가져오기

        // 1. 회원이 빌려간 책이 있는지 확인
        Member member = memberRepository.findById(phone)
                .orElseThrow(() -> new RuntimeException("회원을 찾을 수 없습니다."));

        // 2. 회원이 이미 빌려간 책이 있는 경우 예외처리
        if (alreadyRent(phone)) {
            throw new RuntimeException("회원이 이미 빌려간 책이 있어 대출할 수 없습니다.");
        }

        Long bookId = requestDto.getBookId();
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("책을 찾을 수 없습니다."));

        // 3. 회원이 빌려간 책이 없지만, 책이 이미 다른 회원이 빌려간 상태면 예외처리
        if (!book.isAvailable()) {
            throw new RuntimeException("다른 회원이 이미 이 책을 대출 중입니다.");
        }

        // 4. 대출 가능한 경우 빌려주고 책 상태를 빌려간 상태로 변경
        Rental rental = new Rental();
        rental.setMember(member);
        rental.setBook(book);
        rental.setRented(true);

        rentalRepository.save(rental);

        book.setAvailable(false);
        bookRepository.save(book);

        RentalResponseDto rentalResponseDto = new RentalResponseDto(rental);
        return rentalResponseDto;
    }

    private boolean alreadyRent(String phone) {
        // 회원이 빌려간 책이 있는지 확인하는 로직을 구현
        List<Rental> rentals = rentalRepository.findByMemberPhone(phone);
        return !rentals.isEmpty();
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
