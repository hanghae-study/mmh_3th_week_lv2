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

        // 핸드폰번호로 회원을 찾아서 -> 없으면 예외처리
        Member member = memberRepository.findById(phone)
                .orElseThrow(() -> new RuntimeException("회원을 찾을 수 없습니다."));

        // 핸드폰번호로 조회 -> 책의 반납여부 확인
        if (unReturnedBooks(phone)) {
            throw new RuntimeException("회원이 반납하지 않은 책이 있어 대출할 수 없습니다.");
        }

        Long bookId = requestDto.getBookId();

        // 책의 값으로 조회해서 해당 책 있나 확인
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("책을 찾을 수 없습니다."));

        // 책의 상태를 확인하고 변경
        // 책을 이미 다른 회원이 빌린경우 대출불가, 혹은 다른이유로 빌릴수 없는 경우 (결국 t/f 둘다 책을 빌릴수 없음)
        //
        checkAndSetBookStatus(book, true); // true는 대출 가능 상태

        // 새로 Rental을 만들어주고 ->
        // 회원정보, 책의 정보, 대여가능 여부, 시간을 하나씩 가져와서 저장후에 -> 반환
        Rental rental = new Rental();
        rental.setMember(member);
        rental.setBook(book);
        rental.setRented(true);
        rental.setRentalDate(LocalDateTime.now());
        rentalRepository.save(rental);

        return new RentalResponseDto(rental);
    }

    private boolean unReturnedBooks(String phone) {

        // 반납하지 않은 책이 있는지 확인
        List<Rental> unreturnedRentals = rentalRepository.findByMemberPhoneAndRentedIsFalse(phone);
        // rental이 비어있지 않으면! -> 비어있어야 대여가능이라 비어있지 않으면 예외처리로 넘어감
        return !unreturnedRentals.isEmpty();
    }

    private void checkAndSetBookStatus(Book book, boolean availability) {
        // 책 대여가능 여부가 불가능하다면?
        // availability -> T/f 둘다 빌릴수 없는상태임
        if (!book.isAvailable() == availability) {
            throw new RuntimeException(availability ? "선택한 책은 이미 대출 중입니다." : "선택한 책은 대출 가능하지 않습니다.");
        }
        // isAvailable이 true면 대여가능인데 -> f : f 일때 예외처리 먼저 되고 ->
        // availability !f -> 여기서 t로 되고. 위에서 대여가능 상태됨
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
