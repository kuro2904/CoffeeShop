package vn.com.ltdt.Coffee_Shop.utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import vn.com.ltdt.Coffee_Shop.role.Role;
import vn.com.ltdt.Coffee_Shop.role.RoleRepository;
import vn.com.ltdt.Coffee_Shop.user.Employee;
import vn.com.ltdt.Coffee_Shop.user.repository.EmployeeRepository;

@RequiredArgsConstructor
@Slf4j
@Component
public class SeedDataRunner implements CommandLineRunner {

    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) {

        Role role = roleRepository.findByName("ROLE_EMPLOYEE").orElseGet(
                () -> {
                    Role newRole = new Role();
                    newRole.setName("ROLE_EMPLOYEE");
                    return roleRepository.save(newRole);
                }
        );
        Employee employee = employeeRepository.findByEmail("admin@admin.com");
        if(employee == null) {
            employee = new Employee();
            employee.setName("trung2001sgp");
            employee.setEmail("admin@admin.com");
            employee.setPassword(passwordEncoder.encode("admin"));
            employee.setAddress("123 Street");
            employee.setWebsite("www.google.com");
            employee.setPhoneNumber("123456789");
            employee.setRole(role);
            employeeRepository.save(employee);
        }

    }
}
