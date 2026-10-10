package co.edu.cesde.ga.infrastructure.adapter.in.rest.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupSubjectsResponseDTO {

    private Long groupSubjectId;
    private Long groupId;
    private Long subjectId;
    private Long teacherId;
}

