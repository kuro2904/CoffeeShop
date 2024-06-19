package vn.com.ltdt.Coffee_Shop.role.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import vn.com.ltdt.Coffee_Shop.exceptions.CoffeeShopException;
import vn.com.ltdt.Coffee_Shop.exceptions.ResourceNotFound;
import vn.com.ltdt.Coffee_Shop.utils.mappers.RoleMapper;
import vn.com.ltdt.Coffee_Shop.role.Role;
import vn.com.ltdt.Coffee_Shop.role.RoleDTO;
import vn.com.ltdt.Coffee_Shop.role.RoleRepository;
import vn.com.ltdt.Coffee_Shop.role.service.RoleService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Override
    public List<RoleDTO> getAllRoles() {
        return roleRepository
                .findAll()
                .stream()
                .map(roleMapper::mapToDTO)
                .toList();
    }

    @Override
    public RoleDTO getRole(int id) {
        return roleMapper.mapToDTO(roleRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Role", "Id", String.valueOf(id))
        ));
    }

    @Override
    public RoleDTO insertRole(RoleDTO req) {
        Optional<Role> role = roleRepository.findByName(req.name());
        if(role.isPresent()) {
            throw new CoffeeShopException("The Role name is already Exists", HttpStatus.BAD_REQUEST);
        }
        return roleMapper.mapToDTO(roleRepository.save(
                Role.builder()
                        .id(req.id())
                        .name(req.name())
                        .build())
        );
    }

    @Override
    public RoleDTO updateRole(int id, RoleDTO req) {
        Role role = roleRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Role", "Id", String.valueOf(id)));
        role.setName(req.name());
        return roleMapper.mapToDTO(roleRepository.save(role));
    }

    @Override
    public void deleteRole(int id) {
        Role role = roleRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Role", "Id", String.valueOf(id)));
        roleRepository.delete(role);
    }
}
