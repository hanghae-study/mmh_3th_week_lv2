package com.level2.books.controller;

import com.level2.books.dto.RentalRequestDto;
import com.level2.books.dto.RentalResponseDto;
import com.level2.books.service.RentalService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RentalController {

    private final RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @PostMapping("/books/rent/{bookId}")
    public RentalResponseDto createRent(
            @PathVariable Long id,
            @RequestParam String RRN,
            @RequestBody RentalRequestDto rentalRequestDto) {
        // 요청에서 bookId와 rentalRequestDto를 사용하여 대출 기록 생성 및 대출 가능 여부를 확인하는 로직
        return rentalService.createRent(id, RRN, rentalRequestDto);
    }

    // 특정 회원의 대출 내역 조회
    @GetMapping("/books/rent/{memberId}")
    public List<RentalResponseDto> getRentByMemberId(@PathVariable Long memberId) {
        return rentalService.getRentByMemberId(memberId);
    }

    // 특정 책 반납
    @PutMapping("/books/rent/{bookId}")
    public RentalResponseDto updateRentStatus(@PathVariable Long bookId) {
        return rentalService.updateRentStatus(bookId);
    }
}
