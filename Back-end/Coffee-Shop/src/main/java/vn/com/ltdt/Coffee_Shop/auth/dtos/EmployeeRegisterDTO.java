package vn.com.ltdt.Coffee_Shop.auth.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record EmployeeRegisterDTO(
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
        @NotNull(message = "The address cannot be null")
        String address,
        String website,
        int roleId
) {
}
