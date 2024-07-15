package vn.com.ltdt.Coffee_Shop.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.com.ltdt.Coffee_Shop.auth.dtos.LoginRequest;
import vn.com.ltdt.Coffee_Shop.auth.dtos.AuthResponse;
import vn.com.ltdt.Coffee_Shop.user.dtos.CustomerDTO;
import vn.com.ltdt.Coffee_Shop.user.dtos.EmployeeDTO;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin
public class AuthController {

    private final AuthService authService;
    @PostMapping("login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(new AuthResponse(authService.login(loginRequest.email(), loginRequest.password())));
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
