package co.edu.cesde.ga.infrastructure.adapter.in.rest.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserResponseDTO {

    private Long userId;
    private String username;
    private String email;
    private String status;
    private LocalDateTime createdAt;
}


