package vn.com.ltdt.Coffee_Shop.exceptions;

import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler{

    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ErrorDetail> handleResourceNotFound(ResourceNotFound resourceNotFound, WebRequest webRequest){
        ErrorDetail response = new ErrorDetail(LocalDateTime.now(),resourceNotFound.getMessage(),webRequest.getDescription(false));
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CoffeeShopException.class)
    public ResponseEntity<ErrorDetail> handleCoffeeShopApiException(CoffeeShopException exception,
                                                               WebRequest webRequest){
        ErrorDetail errorDetails = new ErrorDetail(
                LocalDateTime.now(),exception.getMessage(),
                webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorDetail> handleAccessDeniedException(AccessDeniedException exception,
                                                                    WebRequest webRequest){
        ErrorDetail errorDetails = new ErrorDetail(
                LocalDateTime.now(),exception.getMessage(),
                webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetail> handleGlobalException(Exception exception,
                                                              WebRequest webRequest){
        ErrorDetail errorDetails = new ErrorDetail(
                LocalDateTime.now(),exception.getMessage(),
                webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
