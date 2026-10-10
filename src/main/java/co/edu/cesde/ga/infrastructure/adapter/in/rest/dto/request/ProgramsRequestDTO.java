package co.edu.cesde.ga.infrastructure.adapter.in.rest.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProgramsRequestDTO {

    @NotBlank(message = "El código del programa es obligatorio")
    private String code;

    @NotBlank(message = "El nombre del programa es obligatorio")
    private String name;
}
