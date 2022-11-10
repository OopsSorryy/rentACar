package com.yunusAhmet.rentACar.core.exception;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;


import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    @NonNull
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                 @NonNull HttpHeaders headers,
                                                                  @NonNull HttpStatus status,
                                                                   @NonNull WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error ->{
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(CarNotFoundException.class)
    public ResponseEntity<?> carNotFoundExceptionHandler(CarNotFoundException exception)  {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(BrandNotFoundException.class)
    public ResponseEntity<?> brandNotFoundExceptionHandler(BrandNotFoundException exception)  {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ColorNotFoundException.class)
    public ResponseEntity<?> colorNotFoundExceptionHandler(ColorNotFoundException exception)  {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<?> customerNotFoundExceptionHandler(CustomerNotFoundException exception)  {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(CarNotDeliverException.class)
    public ResponseEntity<?> carNotDeliverExceptionHandler(CarNotDeliverException exception)  {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ImageNotFoundException.class)
    public ResponseEntity<?> imageNotFoundExceptionHandler(ImageNotFoundException exception)  {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ColorAlreadyExistException.class)
    public ResponseEntity<?> colorAlreadyExistExceptionHandler(ColorAlreadyExistException exception)  {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT);
    }
    @ExceptionHandler(BrandAlreadyExistException.class)
    public ResponseEntity<?> brandAlreadyExistExceptionHandler(BrandAlreadyExistException exception)  {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT);
    }
    @ExceptionHandler(CustomerEmailAlreadyExistException.class)
    public ResponseEntity<?> customerEmailAlreadyExistExceptionHandler(CustomerEmailAlreadyExistException exception)  {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT);
    }
    @ExceptionHandler(CustomerAlreadyRentACar.class)
    public ResponseEntity<?> customerAlreadyRentACarExceptionHandler(CustomerAlreadyRentACar exception)  {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT);
    }
    @ExceptionHandler(WrongReturnDateException.class)
    public ResponseEntity<?> wrongReturnDateExceptionHandler(WrongReturnDateException exception)  {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(MaxImageException.class)
    public ResponseEntity<?>maxImageExceptionHandler(MaxImageException exception)  {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(TokenNotValidException.class)
    public ResponseEntity<?>tokenNotValidExceptionHandler(TokenNotValidException exception)  {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }



}
