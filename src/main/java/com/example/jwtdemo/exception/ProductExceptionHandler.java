package com.example.jwtdemo.exception;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@RestController
@ControllerAdvice()
public class ProductExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> BadRequestException(MethodArgumentNotValidException ex, WebRequest req){
        ProductExeptionResponse res = new ProductExeptionResponse();
        res.setDate(System.currentTimeMillis());
        res.setStatus(HttpStatus.BAD_REQUEST.value());
        res.setMessage(ex.getMessage());
        return new ResponseEntity<Object>(res,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProductException.class)
    public ResponseEntity<Object> producNotFoundException(ProductException ex, WebRequest req){
        ProductExeptionResponse res = new ProductExeptionResponse();
        res.setDate(System.currentTimeMillis());
        res.setStatus(HttpStatus.NOT_FOUND.value());
        res.setMessage(ex.getMessage());
        return new ResponseEntity<Object>(res,HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ProductExeptionResponse res = new ProductExeptionResponse();
        res.setDate(System.currentTimeMillis());
        res.setStatus(HttpStatus.BAD_REQUEST.value());
        res.setMessage(ex.getMessage());
        return super.handleMethodArgumentNotValid(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ProductExeptionResponse res = new ProductExeptionResponse();
        res.setDate(System.currentTimeMillis());
        res.setStatus(HttpStatus.BAD_REQUEST.value());
        res.setMessage("must provide a valid integer value");

        return new ResponseEntity<Object>(res,HttpStatus.BAD_REQUEST);
    }
}
