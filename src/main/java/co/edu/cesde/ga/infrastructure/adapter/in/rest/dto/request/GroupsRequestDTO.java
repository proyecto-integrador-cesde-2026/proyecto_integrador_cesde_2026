package co.edu.cesde.ga.infrastructure.adapter.in.rest.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupsRequestDTO {

    @NotBlank(message = "El código del grupo es obligatorio")
    private String code;

    @NotNull(message = "El programa es obligatorio")
    private Long programId;

    @NotNull(message = "El período es obligatorio")
    private Long periodId;

    @NotBlank(message = "La jornada es obligatoria")
    private String shift;

}