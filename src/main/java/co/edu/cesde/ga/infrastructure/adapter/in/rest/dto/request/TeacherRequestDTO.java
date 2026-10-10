package co.edu.cesde.ga.infrastructure.adapter.in.rest.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeacherRequestDTO {

    @NotNull(message = "El ID del profesor es obligatorio")
    private Long teacherId;

    @NotNull(message = "El ID del usuario es obligatorio")
    private Long userId;

    private String code;

    @NotBlank(message = "El documento es obligatorio")
    private String documentNumber;

    @NotBlank(message = "El nombre es obligatorio")
    private String firstName;

    @NotBlank(message = "El apellido es obligatorio")
    private String lastName;

    @NotBlank(message = "El estado es obligatorio")
    private String status;
}

