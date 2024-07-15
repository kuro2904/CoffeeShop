package vn.com.ltdt.Coffee_Shop.auth.dtos;

import lombok.Getter;

@Getter
public class AuthResponse {
    private final String type = "Bearer ";
    private final String token;

    public AuthResponse(String token) {
        this.token = token;
    }
}
