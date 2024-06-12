package vn.com.ltdt.Coffee_Shop.auth.dtos;

import lombok.Getter;

@Getter
public class LoginResponse {
    private final String type = "Bearer ";
    private final String token;

    public LoginResponse(String token) {
        this.token = token;
    }
}
