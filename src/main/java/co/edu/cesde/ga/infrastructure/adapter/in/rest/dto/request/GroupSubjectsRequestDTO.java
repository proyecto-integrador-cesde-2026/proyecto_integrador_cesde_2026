package co.edu.cesde.ga.infrastructure.adapter.in.rest.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupSubjectsRequestDTO {

    @NotNull(message = "El grupo es obligatorio")
    private Long groupId;

    @NotNull(message = "La materia es obligatoria")
    private Long subjectId;

    @NotNull(message = "El profesor es obligatorio")
    private Long teacherId;
}

