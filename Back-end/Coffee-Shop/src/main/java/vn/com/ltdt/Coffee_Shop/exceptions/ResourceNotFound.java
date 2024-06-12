package vn.com.ltdt.Coffee_Shop.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFound extends RuntimeException  {

    private final String message;
    private final String fieldName;
    private final String fieldValue;

    public ResourceNotFound(String message, String fieldName, String fieldValue) {
        super(String.format("%s %s %s", message, fieldName, fieldValue));
        this.message = message;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
