package com.level2.books.exception;

public class MemberNotFoundException extends RuntimeException {

    // 주민번호 잘못 입력했을때 예외처리 클래스
    public MemberNotFoundException(String message) {
        super(message);
    }
}
