package vn.com.ltdt.Coffee_Shop.role.service;

import vn.com.ltdt.Coffee_Shop.role.RoleDTO;

import java.util.List;

public interface RoleService {
    List<RoleDTO> getAllRoles();
    RoleDTO getRole(int id);
    RoleDTO insertRole(RoleDTO req);
    RoleDTO updateRole(int id,RoleDTO req);
    void deleteRole(int id);
}
