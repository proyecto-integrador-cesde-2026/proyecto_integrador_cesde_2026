package co.edu.cesde.ga.infrastructure.adapter.in.rest.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnrollmentResponseDTO {

    private Long enrollmentId;
    private Long studentId;
    private Long groupId;
    private Long periodId;
    private String status;
}

