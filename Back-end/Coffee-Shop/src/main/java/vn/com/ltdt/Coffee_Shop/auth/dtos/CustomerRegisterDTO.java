package vn.com.ltdt.Coffee_Shop.auth.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerRegisterDTO(
        String id,
        @NotNull(message = "The name cannot be null")
        String name,
        @NotNull(message = "The email cannot be null")
        @Email(message = "The email is not formatted")
        String email,
        @NotNull(message = "The password cannot be null")
        String password,
        @NotNull(message = "The phone number cannot be null")
        String phoneNumber,
        String address,
        String avatarUrl
) {
}
