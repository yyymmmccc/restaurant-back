package com.example.food.handler;

import com.example.food.common.ResponseCode;
import com.example.food.dto.response.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@RestControllerAdvice
@Slf4j
public class CustomExceptionHandler {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity handleCustomException(CustomException e){

        ResponseCode responseCode = e.getResponseCode();

        HttpStatus status = responseCode.getStatus();
        String code = responseCode.getCode();
        String message = responseCode.getMessage();

        ResponseDto responseDto = new ResponseDto(code, message, null);

        return ResponseEntity.status(status).body(responseDto);
    }

    @ExceptionHandler(WebClientResponseException.class)
    public ResponseEntity handleWebClientResponseException(WebClientResponseException e) {
        ResponseDto responseDto = new ResponseDto("SE", "서버 오류입니다.", null);
        return ResponseEntity.status(e.getStatusCode()).body(responseDto);
    }
}
