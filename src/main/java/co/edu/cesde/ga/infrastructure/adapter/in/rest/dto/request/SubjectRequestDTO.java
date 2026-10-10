
package co.edu.cesde.ga.infrastructure.adapter.in.rest.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubjectRequestDTO {

    @NotBlank(message = "El código de la materia es obligatorio")
    private String code;

    @NotBlank(message = "El nombre de la materia es obligatorio")
    private String name;

    @Positive(message = "Los créditos deben ser mayores que cero")
    private int credits;

    @NotNull(message = "El programa es obligatorio")
    private Long programId;
}