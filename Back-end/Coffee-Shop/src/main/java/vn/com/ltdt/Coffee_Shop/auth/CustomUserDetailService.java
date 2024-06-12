package vn.com.ltdt.Coffee_Shop.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vn.com.ltdt.Coffee_Shop.user.Customer;
import vn.com.ltdt.Coffee_Shop.user.Employee;
import vn.com.ltdt.Coffee_Shop.user.repository.CustomerRepository;
import vn.com.ltdt.Coffee_Shop.user.repository.EmployeeRepository;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {

    private final CustomerRepository customerRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByEmail(username);
        Employee employee = employeeRepository.findByEmail(username);
        if (customer == null && employee == null) {
            throw new UsernameNotFoundException("User not found");
        }else return Objects.requireNonNullElse(customer, employee);
    }
}
