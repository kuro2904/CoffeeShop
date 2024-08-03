package vn.com.ltdt.Coffee_Shop.auth;

import vn.com.ltdt.Coffee_Shop.auth.dtos.CustomerRegisterDTO;
import vn.com.ltdt.Coffee_Shop.auth.dtos.EmployeeRegisterDTO;

public interface AuthService {
    String login(String username, String password);
    String signupCustomer(CustomerRegisterDTO request);
    String signupEmployee(EmployeeRegisterDTO request);
}
