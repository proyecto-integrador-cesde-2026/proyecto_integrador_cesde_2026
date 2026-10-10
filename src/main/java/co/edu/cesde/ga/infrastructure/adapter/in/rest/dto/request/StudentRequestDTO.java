package co.edu.cesde.ga.infrastructure.adapter.in.rest.dto.request;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentRequestDTO {
    @NotBlank(message = "El código es obligatorio")
    private String code;
    @NotBlank(message = "El documento es obligatorio")
    private String documentNumber;
    @NotBlank(message = "El nombre es obligatorio")
    private String firstName;
    @NotBlank(message = "El apellido es obligatorio")
    private String lastName;
    @Pattern(
            regexp = "ACTIVE|INACTIVE",
            message = "El estado debe ser ACTIVE o INACTIVE"
    )
    @NotBlank(message = "El estado es obligatorio")
    private String status;
    private String birthDate;
}
