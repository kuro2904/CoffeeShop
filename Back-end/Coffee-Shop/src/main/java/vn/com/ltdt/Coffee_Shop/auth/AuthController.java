package vn.com.ltdt.Coffee_Shop.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.com.ltdt.Coffee_Shop.auth.dtos.LoginRequest;
import vn.com.ltdt.Coffee_Shop.auth.dtos.AuthResponse;
import vn.com.ltdt.Coffee_Shop.auth.dtos.CustomerRegisterDTO;
import vn.com.ltdt.Coffee_Shop.auth.dtos.EmployeeRegisterDTO;

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
    public ResponseEntity<AuthResponse> signUpEmployee(@RequestBody EmployeeRegisterDTO employeeRegisterDTO) {
        return new ResponseEntity<>(new AuthResponse(authService.signupEmployee(employeeRegisterDTO)), HttpStatus.CREATED);
    }

    @PostMapping("signUp/customer")
    public ResponseEntity<AuthResponse> signUpCustomer(@RequestBody CustomerRegisterDTO customerRegisterDTO) {
        return new ResponseEntity<>(new AuthResponse(authService.signupCustomer(customerRegisterDTO)), HttpStatus.CREATED);
    }
}
