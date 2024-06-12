package vn.com.ltdt.Coffee_Shop.user.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.com.ltdt.Coffee_Shop.user.Employee;
import vn.com.ltdt.Coffee_Shop.user.repository.EmployeeRepository;
import vn.com.ltdt.Coffee_Shop.user.services.EmployeeService;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public Employee getEmployee(String email) {
        Employee employee = null;
        return employeeRepository.findByEmail(email);
    }
}
