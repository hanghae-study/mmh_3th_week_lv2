package com.level2.books.controller;

import com.level2.books.dto.RentalRequestDto;
import com.level2.books.dto.RentalResponseDto;
import com.level2.books.dto.ReturnRequestDto;
import com.level2.books.dto.ReturnResponseDto;
import com.level2.books.service.RentalService;
import com.level2.books.service.ReturnService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RentalController {

    private final RentalService rentalService;

    // 책id 값으로 대여  -> 전달은 되는데 null 값나옴
    @PostMapping("/books/rent/{bookId}")
    public RentalResponseDto createRent(@RequestBody RentalRequestDto rentalRequestDto) {
        return rentalService.createRent(rentalRequestDto);
    }

    // 특정 회원의 대출 내역 조회 - 전화번호 조회
    @GetMapping("/books/rent/{phone}")
    public List<RentalResponseDto> getRentByMemberId(@PathVariable String phone) {
        return rentalService.getRentByMemberId(phone);
    }

}
