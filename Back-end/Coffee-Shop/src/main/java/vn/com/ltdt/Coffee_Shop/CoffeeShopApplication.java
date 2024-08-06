package vn.com.ltdt.Coffee_Shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import vn.com.ltdt.Coffee_Shop.user.Employee;
import vn.com.ltdt.Coffee_Shop.user.repository.EmployeeRepository;

@SpringBootApplication
public class CoffeeShopApplication {


    public static void main(String[] args) {
        SpringApplication.run(CoffeeShopApplication.class, args);
    }

}
