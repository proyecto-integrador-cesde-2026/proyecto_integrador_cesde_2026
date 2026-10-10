package co.edu.cesde.ga.infrastructure.adapter.in.rest;

import co.edu.cesde.ga.domain.model.UserRole;
import co.edu.cesde.ga.domain.model.port.in.UserRoleService;
import co.edu.cesde.ga.infrastructure.adapter.in.rest.dto.request.UserRoleRequestDTO;
import co.edu.cesde.ga.infrastructure.adapter.in.rest.dto.response.UserRoleResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user-roles")
public class UserRoleController {

    private final UserRoleService userRoleService;

    public UserRoleController(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @GetMapping
    public ResponseEntity<List<UserRole>> findAll() {
        return ResponseEntity.ok(userRoleService.findAll());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserRole>> findByUserId(
            @PathVariable Long userId) {
        return ResponseEntity.ok(userRoleService.findByUserId(userId));
    }

    @GetMapping("/role/{roleId}")
    public ResponseEntity<List<UserRole>> findByRoleId(
            @PathVariable Long roleId) {
        return ResponseEntity.ok(userRoleService.findByRoleId(roleId));
    }

    @GetMapping("/exists/{userId}/{roleId}")
    public ResponseEntity<Boolean> existsByUserIdAndRoleId(
            @PathVariable Long userId,
            @PathVariable Long roleId) {
        return ResponseEntity.ok(
                userRoleService.existsByUserIdAndRoleId(userId, roleId)
        );
    }

    @PostMapping
    public ResponseEntity<UserRoleResponseDTO> create(
            @Valid @RequestBody UserRoleRequestDTO dto) {

        UserRole userRole = new UserRole(
                dto.getUserId(),
                dto.getRoleId()
        );

        UserRole createdUserRole = userRoleService.create(userRole);

        UserRoleResponseDTO response = new UserRoleResponseDTO();
        response.setUserId(createdUserRole.getUserId());
        response.setRoleId(createdUserRole.getRoleId());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @DeleteMapping("/{userId}/{roleId}")
    public ResponseEntity<Void> delete(
            @PathVariable Long userId,
            @PathVariable Long roleId) {

        userRoleService.delete(userId, roleId);

        return ResponseEntity.noContent().build();
    }
}
