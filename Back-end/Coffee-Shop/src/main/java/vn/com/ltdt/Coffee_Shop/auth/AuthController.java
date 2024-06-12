package vn.com.ltdt.Coffee_Shop.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.com.ltdt.Coffee_Shop.auth.dtos.LoginRequest;
import vn.com.ltdt.Coffee_Shop.auth.dtos.LoginResponse;
import vn.com.ltdt.Coffee_Shop.user.dtos.CustomerDTO;
import vn.com.ltdt.Coffee_Shop.user.dtos.EmployeeDTO;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;
    @PostMapping("login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(new LoginResponse(authService.login(loginRequest.email(), loginRequest.password())));
    }

    @PostMapping("signUp/employee")
    public ResponseEntity<EmployeeDTO> signUpEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return new ResponseEntity<>(authService.signupEmployee(employeeDTO), HttpStatus.CREATED);
    }

    @PostMapping("signUp/customer")
    public ResponseEntity<CustomerDTO> signUpCustomer(@RequestBody CustomerDTO customerDTO) {
        return new ResponseEntity<>(authService.signupCustomer(customerDTO), HttpStatus.CREATED);
    }
}
