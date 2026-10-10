package co.edu.cesde.ga.infrastructure.adapter.in.rest.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GradeRequestDTO {

    @NotNull(message = "El grupo de materia es obligatorio")
    private Long groupSubjectId;

    @NotNull(message = "El estudiante es obligatorio")
    private Long studentId;

    @Min(value = 0, message = "La nota mínima es 0")
    @Max(value = 5, message = "La nota máxima es 5")
    private Integer finalScore;

    @NotBlank(message = "La observación es obligatoria")
    private String observation;
}

