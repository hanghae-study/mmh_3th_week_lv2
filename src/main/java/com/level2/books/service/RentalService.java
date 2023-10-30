package com.level2.books.service;

import com.level2.books.dto.RentalRequestDto;
import com.level2.books.dto.RentalResponseDto;
import com.level2.books.entity.Book;
import com.level2.books.entity.Rental;
import com.level2.books.exception.RentalNotFoundException;
import com.level2.books.repository.RentalRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class RentalService {
    private final RentalRepository rentalRepository; // 렌탈 레포지토리 주입

    public RentalService(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    public RentalResponseDto createRent(Long id, String RRN, RentalRequestDto requestDto) {

        // RequestDto -> Entity
        Rental rental = new Rental(requestDto);

        // DB 저장
        Rental saveRental = rentalRepository.save(rental);

        // Entity - > ResponseDto
        RentalResponseDto rentalResponseDto = new RentalResponseDto(saveRental);
        return rentalResponseDto;

        // 반납하지 않은 책이 있는지 확인 (대출 중인 도서 목록 조회)
        List<Rental> rentals = rentalRepository.findByMemberIdAndReturnStatusIsFalse(RRN);

    }

    // 특정 회원의 대출 내역 조회
    public List<RentalResponseDto> getRentByMemberId(String RRN) {

        // 대출내역을 조회를 먼저하고 -> 매핑하는 이유는
        // 조회를 해서 나오는 건 엔티티 객체 형태로 반환되어서 -> 사용자에게 반환하기 전에 엔티티를
        // -> Dto로 형태로 변환해서 반환하는 방법이 좀더 맞는 쪽이라서

        // 주민등록번호를 사용하여 해당 회원의 대출 내역을 전체 조회
        List<Rental> rentals = rentalRepository.findByMemberIdAndReturnStatusIsFalse(RRN);

        // 위에서 조회한 대출내역 엔티티 객체 -> Dto로 매핑하고 -> 매핑된 객체들을 리스트 형식으로 반환함
        List<RentalResponseDto> rentalList = rentals.stream()
                .map(RentalResponseDto::new)
                .collect(Collectors.toList());

        // TODO 대출 내역 기록에는 회원의 `이름`과 `전화번호`, 도서의 `제목`과 `저자`가 포함 되어있어야 합니다.
        // 조회된 대출 내역 기록은 `대출일` 기준 오름차순으로 정렬 되어있습니다

        return rentalList;
    }

    // 선택한 책 반납
    public RentalResponseDto updateRentStatus(Long id) { // 책의 고유값을 받아와서 처리

        // 대출 내역 확인 -> 대출내역이 없으면 없다고 예외처리
        Rental rental = rentalRepository.findById(id).orElseThrow(
                () -> new RentalNotFoundException("대출 내역을 찾을 수 없습니다."));

        // 대출 내역 업데이트
        rental.setReturnStatus(true); // 반납상태 변경
        rental.setReturnDate(LocalDateTime.now()); // 현재 시간으로 반납일 변경

        // 책의 대출 상태 업데이트
        Book book = rental.getBook();
        book.setRented(false); // 반납상태 변경

        // 대출 내역 저장
        rentalRepository.save(rental);

        // 변경된 대출 내역을 dto로 변환하여 반환
        return new RentalResponseDto(rental);
    }
}
