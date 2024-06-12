package vn.com.ltdt.Coffee_Shop.role;

import jakarta.validation.constraints.NotNull;

public record RoleDTO(
        int id,
        @NotNull(message = "The name cannot be null")
        String name
) {
}
