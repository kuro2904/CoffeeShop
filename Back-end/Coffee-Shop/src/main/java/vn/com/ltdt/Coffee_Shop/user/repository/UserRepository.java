package vn.com.ltdt.Coffee_Shop.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import vn.com.ltdt.Coffee_Shop.user.User;

@NoRepositoryBean
public interface UserRepository<T extends User> extends JpaRepository<T, String> {
    T findByEmail(String email);
}
