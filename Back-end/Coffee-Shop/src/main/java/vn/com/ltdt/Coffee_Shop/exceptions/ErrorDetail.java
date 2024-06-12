package vn.com.ltdt.Coffee_Shop.exceptions;

import java.time.LocalDateTime;

public record ErrorDetail(
    LocalDateTime dateTime,
    String message,
    String detail
) {
}
