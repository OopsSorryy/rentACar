package com.yunusAhmet.rentACar.core.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {

    @NotNull
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  @NotNull HttpHeaders headers,
                                                                  @NotNull HttpStatus status,
                                                                  @NotNull WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error ->{
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BrandNotFoundException.class)
    public ResponseEntity<?> brandNotFoundExceptionHandler(BrandNotFoundException exception)  {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ColorNotFoundException.class)
    public ResponseEntity<?> colorFoundExceptionHandler(ColorNotFoundException exception)  {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(CarNotFoundException.class)
    public ResponseEntity<?> carNotFoundExceptionHandler(CarNotFoundException exception)  {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
}
