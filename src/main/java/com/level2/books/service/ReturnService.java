package com.level2.books.service;

import com.level2.books.dto.ReturnRequestDto;
import com.level2.books.dto.ReturnResponseDto;
import com.level2.books.entity.Return;
import com.level2.books.repository.ReturnRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReturnService {

    private final ReturnRepository returnRepository;

    public ReturnResponseDto updateReturnStatus(ReturnRequestDto requestDto) {

        // ReturnRequestDto에서 반납 정보 추출
        Long returnId = requestDto.getReturnId();
        boolean returnStatus = requestDto.isReturnStatus();

        // 반납 정보 업데이트
        Return returnEntity = returnRepository.findById(returnId)
                .orElseThrow(() -> new IllegalArgumentException("반납 정보를 찾을 수 없음"));
        returnEntity.setReturnStatus(returnStatus);
        returnRepository.save(returnEntity);

        // 업데이트된 정보를 ReturnResponseDto로 변환
        ReturnResponseDto responseDto = new ReturnResponseDto();
        responseDto.setReturnId(returnId);
        responseDto.setReturnStatus(returnStatus);
        return responseDto;
    }
}
