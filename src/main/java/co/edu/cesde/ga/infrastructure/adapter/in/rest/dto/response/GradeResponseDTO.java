
package co.edu.cesde.ga.infrastructure.adapter.in.rest.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GradeResponseDTO {

    private Long gradeId;
    private Long groupSubjectId;
    private Long studentId;
    private int finalScore;
    private String observation;
}