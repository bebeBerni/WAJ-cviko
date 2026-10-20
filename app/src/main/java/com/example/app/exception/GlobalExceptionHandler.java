package com.example.app.exception;

import com.example.app.response.ResponseFactory;
import com.example.app.response.ResponseWrapper;
import org.hibernate.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final ResponseFactory response;

    public GlobalExceptionHandler(ResponseFactory response) {
        this.response = response;
    }

    // Validačné chyby
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseWrapper<Void>> handleValidation(
            MethodArgumentNotValidException ex) {

        List<Map<String, String>> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> {
                    Map<String, String> map = new HashMap<>();
                    map.put("field", error.getField());
                    map.put("message", error.getDefaultMessage());
                    return map;
                })
                .collect(Collectors.toList());

        return response.error(
                HttpStatus.BAD_REQUEST,
                "Validačné chyby",
                errors
        );
    }

    // Nesprávny typ parametra
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ResponseWrapper<Void>> handleTypeMismatch(
            MethodArgumentTypeMismatchException ex) {

        String message = "Parameter '" + ex.getName()
                + "' má neplatnú hodnotu";

        return response.error(HttpStatus.BAD_REQUEST, message);
    }

    // Objekt nenájdený
    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<ResponseWrapper<Void>> handleObjectNotFound(
            ObjectNotFoundException ex) {

        return response.error(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    // Email už existuje
    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ResponseWrapper<Void>> handleEmailExists(
            EmailAlreadyExistsException ex) {

        return response.error(HttpStatus.CONFLICT, ex.getMessage());
    }

    // Ostatné chyby
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseWrapper<Void>> handleGeneral(
            Exception ex) {

        return response.error(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Nastala neočakávaná chyba"
        );
    }
}