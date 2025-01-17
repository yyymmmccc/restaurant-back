package com.example.food.handler;

import com.example.food.common.ResponseCode;
import com.example.food.dto.response.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class CustomExceptionHandler {

    @ExceptionHandler
    public ResponseEntity handleCustomException(CustomException e){

        ResponseCode responseCode = e.getResponseCode();

        HttpStatus status = responseCode.getStatus();
        String code = responseCode.getCode();
        String message = responseCode.getMessage();

        ResponseDto responseDto = new ResponseDto(code, message, null);

        return ResponseEntity.status(status).body(responseDto);

    }
}
