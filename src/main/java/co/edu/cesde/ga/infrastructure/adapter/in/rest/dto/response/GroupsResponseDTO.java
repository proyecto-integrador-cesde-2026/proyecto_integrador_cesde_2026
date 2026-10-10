package co.edu.cesde.ga.infrastructure.adapter.in.rest.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupsResponseDTO {

    private Long groupId;
    private String code;
    private Long programId;
    private Long periodId;
    private String shift;
}

