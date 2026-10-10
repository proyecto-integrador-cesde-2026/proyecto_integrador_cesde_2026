package co.edu.cesde.ga.infrastructure.adapter.in.rest.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PeriodResponseDTO {

    private Long periodId;
    private String name;
    private String startDate;
    private String endDate;
}

