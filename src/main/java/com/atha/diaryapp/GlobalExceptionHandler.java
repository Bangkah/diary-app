package com.atha.diaryapp;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

// @ControllerAdvice
// public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
//     protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
//                                                                   HttpHeaders headers,
//                                                                   HttpStatus status,
//                                                                   WebRequest request) {
//         Map<String, String> errors = new HashMap<>();
//         ex.getBindingResult().getFieldErrors().forEach(error ->
//                 errors.put(error.getField(), error.getDefaultMessage())
//         );
//         return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
//     }
//
//     @ExceptionHandler(RuntimeException.class)
//     public ResponseEntity<Object> handleRuntimeException(RuntimeException ex) {
//         Map<String, String> error = new HashMap<>();
//         error.put("error", ex.getMessage());
//         return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
//     }
// }
