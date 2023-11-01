package com.level2.books.controller;

import com.level2.books.dto.ReturnRequestDto;
import com.level2.books.dto.ReturnResponseDto;
import com.level2.books.service.ReturnService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ReturnController {

    private final ReturnService returnService;

    @Autowired // 생성자 주입
    public ReturnController(ReturnService returnService) {
        this.returnService = returnService;
    }

    @PutMapping("/books/return/{bookId}")
    public ResponseEntity<ReturnResponseDto> updateReturnStatus(
            @PathVariable Long bookId,
            @RequestBody ReturnRequestDto requestDto) {
        ReturnResponseDto responseDto = returnService.returnBook(bookId, requestDto.getPhone());
        return ResponseEntity.ok(responseDto);
    }
}
