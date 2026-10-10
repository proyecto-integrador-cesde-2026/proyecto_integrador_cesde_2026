package co.edu.cesde.ga.infrastructure.adapter.in.rest.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeacherResponseDTO {

    private Long teacherId;
    private Long userId;
    private String code;
    private String documentNumber;
    private String firstName;
    private String lastName;
    private String status;
}
