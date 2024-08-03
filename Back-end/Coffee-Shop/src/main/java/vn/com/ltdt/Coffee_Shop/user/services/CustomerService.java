package vn.com.ltdt.Coffee_Shop.user.services;

import vn.com.ltdt.Coffee_Shop.user.dtos.CustomerDTO;

public interface CustomerService {
    CustomerDTO getCustomerById(String userId);
}
