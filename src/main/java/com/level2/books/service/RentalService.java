package com.level2.books.service;

import com.level2.books.dto.RentalRequestDto;
import com.level2.books.dto.RentalResponseDto;
import com.level2.books.entity.Rental;
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

    public RentalResponseDto createRent(RentalRequestDto requestDto) {

        // RequestDto -> Entity
        Rental rental = new Rental(requestDto);

        // DB 저장
        Rental saveRental = rentalRepository.save(rental);

        // Entity - > ResponseDto
        RentalResponseDto rentalResponseDto = new RentalResponseDto(saveRental);
        return rentalResponseDto;
    }

    // 특정 회원의 대출 내역 조회
    public List<RentalResponseDto> getRentByMemberId(String phone) {

        // 대출내역 조회
        List<Rental> rentals = rentalRepository.findByMemberIdAndReturnStatusIsFalse(phone);

        // Dto로 매핑
        List<RentalResponseDto> rentalList = rentals.stream()
                .map(RentalResponseDto::new)
                .collect(Collectors.toList());


        // 대출일 기준으로 정렬
        rentalList.sort(Comparator.comparing(RentalResponseDto::getRentalDate));
        return rentalList;
    }
}
