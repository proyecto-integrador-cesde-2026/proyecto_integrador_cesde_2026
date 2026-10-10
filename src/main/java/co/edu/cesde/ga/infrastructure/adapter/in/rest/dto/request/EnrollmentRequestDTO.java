package co.edu.cesde.ga.infrastructure.adapter.in.rest.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnrollmentRequestDTO {

    @NotNull(message = "El estudiante es obligatorio")
    private Long studentId;

    @NotNull(message = "El grupo es obligatorio")
    private Long groupId;

    @NotNull(message = "El período es obligatorio")
    private Long periodId;

    @NotBlank(message = "El estado de la matrícula es obligatorio")
    private String status;
}
