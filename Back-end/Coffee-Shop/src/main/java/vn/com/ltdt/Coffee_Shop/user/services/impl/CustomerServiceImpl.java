package vn.com.ltdt.Coffee_Shop.user.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.com.ltdt.Coffee_Shop.exceptions.ResourceNotFound;
import vn.com.ltdt.Coffee_Shop.user.Customer;
import vn.com.ltdt.Coffee_Shop.user.dtos.CustomerDTO;
import vn.com.ltdt.Coffee_Shop.user.repository.CustomerRepository;
import vn.com.ltdt.Coffee_Shop.user.services.CustomerService;
import vn.com.ltdt.Coffee_Shop.utils.mappers.UserMapper;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final UserMapper userMapper;

    @Override
    public CustomerDTO getCustomerById(String userId) {
        Customer customer = customerRepository.findById(userId).orElseThrow(()-> new ResourceNotFound("Cannot found","UserId", userId));
        return userMapper.mapToCustomerDTO(customer);
    }
}
