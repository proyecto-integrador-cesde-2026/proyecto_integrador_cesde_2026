package co.edu.cesde.ga.infrastructure.adapter.in.rest.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PeriodRequestDTO {

    @NotBlank(message = "El nombre del período es obligatorio")
    private String name;

    @NotBlank(message = "La fecha de inicio es obligatoria")
    private String startDate;

    @NotBlank(message = "La fecha de finalización es obligatoria")
    private String endDate;
}