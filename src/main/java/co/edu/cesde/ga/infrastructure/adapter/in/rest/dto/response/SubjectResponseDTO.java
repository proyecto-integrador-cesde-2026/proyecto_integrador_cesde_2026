package co.edu.cesde.ga.infrastructure.adapter.in.rest.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubjectResponseDTO {

    private Long subjectId;
    private String code;
    private String name;
    private int credits;
    private Long programId;
}

