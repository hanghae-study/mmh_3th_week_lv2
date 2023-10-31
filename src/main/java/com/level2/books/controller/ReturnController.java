package com.level2.books.controller;

import com.level2.books.dto.ReturnRequestDto;
import com.level2.books.dto.ReturnResponseDto;
import com.level2.books.service.ReturnService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReturnController {

    private final ReturnService returnService;

    // 특정 책 반납
    @PutMapping("/books/return/{bookId}")
    public ReturnResponseDto updateReturnStatus(@RequestBody ReturnRequestDto requestDto) {
        return returnService.updateReturnStatus(requestDto);
    }
}
