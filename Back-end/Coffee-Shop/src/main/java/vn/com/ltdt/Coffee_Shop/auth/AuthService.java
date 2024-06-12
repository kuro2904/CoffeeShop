package vn.com.ltdt.Coffee_Shop.auth;

import vn.com.ltdt.Coffee_Shop.user.dtos.CustomerDTO;
import vn.com.ltdt.Coffee_Shop.user.dtos.EmployeeDTO;

public interface AuthService {
    String login(String username, String password);
    CustomerDTO signupCustomer(CustomerDTO request);
    EmployeeDTO signupEmployee(EmployeeDTO request);
}
