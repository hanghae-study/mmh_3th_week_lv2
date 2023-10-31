package com.level2.books.controller;

import com.level2.books.dto.RentalRequestDto;
import com.level2.books.dto.RentalResponseDto;
import com.level2.books.service.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RentalController {

    private final RentalService rentalService;

    // 책id 값으로 대여
    @PostMapping("/books/rent/{bookId}")
    public RentalResponseDto createRent(@RequestBody RentalRequestDto rentalRequestDto) {
        // 요청에서 bookId와 rentalRequestDto를 사용하여 대출 기록 생성 및 대출 가능 여부를 확인하는 로직
        return rentalService.createRent(rentalRequestDto);
    }

    // 특정 회원의 대출 내역 조회 - 전화번호 조회
    @GetMapping("/books/rent/{phone}")
    public List<RentalResponseDto> getRentByMemberId(@PathVariable String phone) {
        return rentalService.getRentByMemberId(phone);
    }

}
