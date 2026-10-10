package co.edu.cesde.ga.infrastructure.adapter.in.rest.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRoleRequestDTO {

    @NotNull(message = "El ID del usuario es obligatorio")
    private Long userId;

    @NotNull(message = "El ID del rol es obligatorio")
    private Long roleId;
}

