package com.level2.books.service;

import com.level2.books.dto.ReturnResponseDto;
import com.level2.books.entity.Book;
import com.level2.books.entity.Member;
import com.level2.books.entity.Rental;
import com.level2.books.repository.BookRepository;
import com.level2.books.repository.MemberRepository;
import com.level2.books.repository.RentalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReturnService {
    private final RentalRepository rentalRepository;
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public ReturnResponseDto returnBook(Long bookId, String phone) {
        ReturnResponseDto responseDto = new ReturnResponseDto();

        // 1. 입력한 bookId에 해당하는 대여 기록을 조회
        Optional<Rental> rentalOptional = rentalRepository.findById(bookId);

        if (rentalOptional.isEmpty()) {
            responseDto.setSuccess(false);
            responseDto.setMessage("대여 기록을 찾을 수 없습니다.");
            return responseDto;
        }

        Rental rental = rentalOptional.get();
        Book book = rental.getBook();
        Member member = rental.getMember();

        // 2. 추가 정보 설정
        responseDto.setBookId(book.getBookId());
        responseDto.setWriter(book.getWriter());
        responseDto.setTitle(book.getTitle());
        responseDto.setPhone(member.getPhone());
        responseDto.setName(member.getName());
        responseDto.setReturnDate(LocalDateTime.now());

        // 3. 대여 상태 변경
        rental.setRented(false);

        rentalRepository.save(rental);
        bookRepository.save(book);

        responseDto.setSuccess(true);
        responseDto.setMessage("책이 성공적으로 반납되었습니다.");

        return responseDto;
    }
}



