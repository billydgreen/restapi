package edu.iu.p565.customerservice.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<?> handleException(IllegalStateException exc){
        return ResponseEntity.badRequest().body(exc.getMessage());
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleException(MethodArgumentNotValidException exc){
        String errMessages = exc.getBindingResult().getFieldErrors().
                stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(","));
        return ResponseEntity.badRequest().body(errMessages);
    }
}
