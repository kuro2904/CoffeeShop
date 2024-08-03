package vn.com.ltdt.Coffee_Shop.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.com.ltdt.Coffee_Shop.user.User;
import vn.com.ltdt.Coffee_Shop.role.Role;
import vn.com.ltdt.Coffee_Shop.role.RoleRepository;
import vn.com.ltdt.Coffee_Shop.user.Customer;
import vn.com.ltdt.Coffee_Shop.user.Employee;
import vn.com.ltdt.Coffee_Shop.auth.dtos.CustomerRegisterDTO;
import vn.com.ltdt.Coffee_Shop.auth.dtos.EmployeeRegisterDTO;
import vn.com.ltdt.Coffee_Shop.user.repository.CustomerRepository;
import vn.com.ltdt.Coffee_Shop.user.repository.EmployeeRepository;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final EmployeeRepository employeeRepository;
    private final CustomerRepository customerRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;


    @Override
    public String login(String username, String password) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtProvider.generateToken((User) authentication.getPrincipal());
    }

    @Override
    public String signupCustomer(CustomerRegisterDTO request) {
        Customer customer = new Customer();
        Role role = roleRepository.findByName("ROLE_CUSTOMER")
        .orElse(roleRepository.save(Role.builder().name("ROLE_CUSTOMER").build()));
        customer.setName(request.name());
        customer.setEmail(request.email());
        customer.setPassword(passwordEncoder.encode(request.password()));
        customer.setAddress(request.address());
        customer.setPhoneNumber(request.phoneNumber());
        customer.setRole(role);
        return jwtProvider.generateToken(customerRepository.save(customer));
    }

    @Override
    public String signupEmployee(EmployeeRegisterDTO request) {
        Role role = roleRepository.findByName("ROLE_EMPLOYEE")
        .orElse(roleRepository.save(Role.builder().name("ROLE_EMPLOYEE").build()));
        Employee employee = new Employee();
        employee.setName(request.name());
        employee.setEmail(request.email());
        employee.setPassword(passwordEncoder.encode(request.password()));
        employee.setAddress(request.address());
        employee.setPhoneNumber(request.phoneNumber());
        employee.setWebsite(request.website());
        employee.setRole(role);
        return jwtProvider.generateToken(employeeRepository.save(employee));
    }


}
