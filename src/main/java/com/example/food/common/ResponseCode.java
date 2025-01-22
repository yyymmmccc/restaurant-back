package com.example.food.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ResponseCode {

    REG_PASSWORD_FAIL(HttpStatus.BAD_REQUEST, "REG_PASSWORD_FAIL", "비밀번호와 비밀번호확인이 서로 다릅니다."),
    LOGIN_FAIL(HttpStatus.BAD_REQUEST, "LOGIN_FAIL", "아이디 또는 비밀번호를 확인해주세요."),

    NOT_FOUND_USER(HttpStatus.NOT_FOUND, "NOT_FOUND_USER", "사용자를 찾을 수 없습니다."),
    NOT_FOUND_WISHLIST(HttpStatus.NOT_FOUND, "NOT_FOUND_WISHLIST", "위시리스트를 찾을 수 없습니다."),
    NOT_FOUND_PLANNER(HttpStatus.NOT_FOUND, "NOT_FOUND_PLANNER", "플래너를 찾을 수 없습니다."),

    DUP_USER_ID(HttpStatus.CONFLICT, "DUP_ID", "중복된 아이디입니다."),
    DUP_NICKNAME(HttpStatus.CONFLICT, "DUP_NICKNAME", "중복된 닉네임입니다."),
    DUP_PLACE(HttpStatus.CONFLICT, "DUP_PLACE", "이미 등록된 장소입니다.");

    private HttpStatus status;
    private String code;
    private String message;
}
