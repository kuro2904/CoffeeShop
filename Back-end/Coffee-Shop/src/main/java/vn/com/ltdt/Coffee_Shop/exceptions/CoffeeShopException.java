package vn.com.ltdt.Coffee_Shop.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CoffeeShopException extends RuntimeException {

    private final String message;
    private final HttpStatus status;

    public CoffeeShopException(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

}
