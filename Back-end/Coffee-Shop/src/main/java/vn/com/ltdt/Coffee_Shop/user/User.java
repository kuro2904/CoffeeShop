package vn.com.ltdt.Coffee_Shop.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.security.core.userdetails.UserDetails;


@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class User implements UserDetails {
    @Id
    @UuidGenerator
    private String id;
    @NotNull(message = "The name cannot be null")
    private String name;
    @Column(unique = true)
    @NotNull(message = "The email cannot be null")
    @Email(message = "The email is not formatted")
    private String email;
    @NotNull(message = "The password cannot be null")
    private String password;
    @NotNull(message = "The phone number cannot be null")
    private String phoneNumber;
    @NotNull(message = "The address cannot be null")
    private String address;

}
