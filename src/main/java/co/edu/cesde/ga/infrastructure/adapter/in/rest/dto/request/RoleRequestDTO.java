package co.edu.cesde.ga.infrastructure.adapter.in.rest.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleRequestDTO {

    @NotNull(message = "El ID del rol es obligatorio")
    private Long roleId;

    @NotBlank(message = "El nombre del rol es obligatorio")
    private String name;

    @NotBlank(message = "La descripción del rol es obligatoria")
    private String description;
}

