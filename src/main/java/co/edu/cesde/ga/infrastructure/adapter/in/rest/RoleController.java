package co.edu.cesde.ga.infrastructure.adapter.in.rest;

import co.edu.cesde.ga.domain.model.Role;
import co.edu.cesde.ga.domain.model.port.in.RoleService;
import co.edu.cesde.ga.infrastructure.adapter.in.rest.dto.request.RoleRequestDTO;
import co.edu.cesde.ga.infrastructure.adapter.in.rest.dto.response.RoleResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<List<Role>> findAll() {
        return ResponseEntity.ok(roleService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> findById(@PathVariable Long id) {
        return ResponseEntity.ok(roleService.findById(id));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Role> findByName(@PathVariable String name) {
        return ResponseEntity.ok(roleService.findByName(name));
    }

    @PostMapping
    public ResponseEntity<RoleResponseDTO> create(
            @Valid @RequestBody RoleRequestDTO dto) {

        Role role = new Role();
        role.setRoleId(dto.getRoleId());
        role.setName(dto.getName());
        role.setDescription(dto.getDescription());

        Role createdRole = roleService.create(role);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(toResponseDTO(createdRole));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoleResponseDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody RoleRequestDTO dto) {

        Role role = new Role();
        role.setRoleId(id);
        role.setName(dto.getName());
        role.setDescription(dto.getDescription());

        boolean updated = roleService.update(role);

        if (!updated) {
            return ResponseEntity.notFound().build();
        }

        Role updatedRole = roleService.findById(id);

        if (updatedRole == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(toResponseDTO(updatedRole));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean deleted = roleService.delete(id);

        if (deleted) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

    private RoleResponseDTO toResponseDTO(Role role) {
        RoleResponseDTO dto = new RoleResponseDTO();

        dto.setRoleId(role.getRoleId());
        dto.setName(role.getName());
        dto.setDescription(role.getDescription());

        return dto;
    }
}

