package com.example.food.handler;


import com.example.food.common.ResponseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CustomException extends RuntimeException{

    private ResponseCode responseCode;

}
