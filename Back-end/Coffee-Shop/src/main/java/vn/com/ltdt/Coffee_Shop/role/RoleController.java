package vn.com.ltdt.Coffee_Shop.role;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.com.ltdt.Coffee_Shop.role.service.RoleService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @PostMapping
    public ResponseEntity<RoleDTO> insert(@RequestBody RoleDTO req) {
        return new ResponseEntity<>(roleService.insertRole(req), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoleDTO> update(@PathVariable int id,@RequestBody RoleDTO req) {

        return new ResponseEntity<>(roleService.updateRole(id, req), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<RoleDTO>> findAll() {
        return new ResponseEntity<>(roleService.getAllRoles(), HttpStatus.OK);
    }

    @GetMapping("/{roleId}")
    public ResponseEntity<RoleDTO> findById(@PathVariable int roleId) {
        return new ResponseEntity<>(roleService.getRole(roleId), HttpStatus.OK);
    }

    @DeleteMapping("/{roleId}")
    public ResponseEntity<RoleDTO> delete(@PathVariable int roleId) {
        roleService.deleteRole(roleId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
