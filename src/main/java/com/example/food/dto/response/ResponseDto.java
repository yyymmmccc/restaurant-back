package com.example.food.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto {
    private String code;
    private String message;
    private Object data;

    public static ResponseEntity success(Object data){
        ResponseDto responseDto = new ResponseDto("SU", "성공", data);
        return ResponseEntity.ok(responseDto);
    }
}
