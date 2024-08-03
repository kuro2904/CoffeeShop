package vn.com.ltdt.Coffee_Shop.user.dtos;

public record EmployeeDTO(
        String name,
        String email,
        String phoneNumber,
        String address,
        String website,
        int roleId
){}
