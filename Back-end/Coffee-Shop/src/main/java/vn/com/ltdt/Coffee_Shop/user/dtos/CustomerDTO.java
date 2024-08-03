package vn.com.ltdt.Coffee_Shop.user.dtos;

public record CustomerDTO(
        String name,
        String email,
        String phoneNumber,
        String address,
        String avatarUrl) {
}
