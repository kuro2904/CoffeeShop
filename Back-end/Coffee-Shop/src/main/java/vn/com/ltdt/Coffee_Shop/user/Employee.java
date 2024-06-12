package vn.com.ltdt.Coffee_Shop.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import vn.com.ltdt.Coffee_Shop.order.Order;
import vn.com.ltdt.Coffee_Shop.role.Role;

import java.util.Collection;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "t_employee")
public class Employee extends User {

    private String website;
    @NotNull(message = "Role cannot be null")
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
    @OneToMany(mappedBy = "employee")
    private List<Order> orders;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.getName()));
    }

    @Override
    public String getUsername() {
        return super.getEmail();
    }
}
