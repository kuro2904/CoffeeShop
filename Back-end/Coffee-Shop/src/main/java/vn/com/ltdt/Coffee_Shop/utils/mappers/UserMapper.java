package vn.com.ltdt.Coffee_Shop.utils.mappers;

import org.springframework.stereotype.Service;
import vn.com.ltdt.Coffee_Shop.user.Customer;
import vn.com.ltdt.Coffee_Shop.user.Employee;
import vn.com.ltdt.Coffee_Shop.user.dtos.CustomerDTO;
import vn.com.ltdt.Coffee_Shop.user.dtos.EmployeeDTO;

@Service
public class UserMapper {

    public EmployeeDTO mapToEmployeeDTO(Employee employee) {
        return new EmployeeDTO(
          employee.getName(),
          employee.getEmail(),
          employee.getPhoneNumber(),
          employee.getAddress(),
          employee.getWebsite(),
          employee.getRole().getId()
        );
    }

    public CustomerDTO mapToCustomerDTO(Customer customer) {
        return new CustomerDTO(
                customer.getName(),
                customer.getEmail(),
                customer.getPhoneNumber(),
                customer.getAddress(),
                customer.getAvatarUrl()
        );
    }

}
