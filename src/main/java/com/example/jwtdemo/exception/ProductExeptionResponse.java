package com.example.jwtdemo.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductExeptionResponse {
    private String message;
    private long date;
    private int status;

}
