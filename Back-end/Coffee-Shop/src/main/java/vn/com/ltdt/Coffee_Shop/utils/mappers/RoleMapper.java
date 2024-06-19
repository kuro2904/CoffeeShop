package vn.com.ltdt.Coffee_Shop.utils.mappers;

import org.springframework.stereotype.Service;
import vn.com.ltdt.Coffee_Shop.role.Role;
import vn.com.ltdt.Coffee_Shop.role.RoleDTO;

@Service
public class RoleMapper {
    public RoleDTO mapToDTO(Role role) {
        return new RoleDTO(role.getId(), role.getName());
    }
}
