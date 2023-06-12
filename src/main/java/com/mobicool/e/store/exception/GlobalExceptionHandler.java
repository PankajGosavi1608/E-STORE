package com.mobicool.e.store.exception;

import com.mobicool.e.store.dto.ApiResponseMessage;
import com.mobicool.e.store.helper.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> ResourceNotFoundExceptionHandling(ResourceNotFoundException rs) {

        logger.info("Exception Handler Invoked");
        //   String msg = rs.getMessage();
        ApiResponseMessage response = ApiResponseMessage.builder().message(rs.getMessage()).status(HttpStatus.NOT_FOUND).success(true).build();
        return new ResponseEntity(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> MethodArgumentNotValidExceptionHandling(
            MethodArgumentNotValidException mx) {
        Map<String, String> map = new HashMap<>();
        mx.getBindingResult().getAllErrors().forEach((error) -> {
            String field = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            map.put(field,message);
        });
        return new ResponseEntity<Map<String, String>>(map, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadApiRequest.class)
    public ResponseEntity<ApiResponse> handleBadApiRequest(BadApiRequest ex) {
        logger.info("Bad Api Request");
        // String msg = rs.getMessage();
        ApiResponseMessage response = ApiResponseMessage.builder().message(ex.getMessage()).status(HttpStatus.BAD_REQUEST).success(false).build();
        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }

}


