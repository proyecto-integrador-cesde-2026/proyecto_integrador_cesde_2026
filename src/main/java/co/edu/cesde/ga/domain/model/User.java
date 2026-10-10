package co.edu.cesde.ga.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    // Un usuario puede tener muchos registros en UserRole
    @OneToMany(mappedBy = "user")
    private List<UserRole> userRoles;

    @NotBlank(message = "El username es obligatorio")
    @Column(nullable = false, unique = true)
    private String username;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email no es válido")
    @Column(nullable = false, unique = true)
    private String email;

    @JsonProperty("password")
    @NotBlank(message = "La contraseña es obligatoria")
    @Column(nullable = false)
    private String passwordHash;

    @NotBlank(message = "El estado es obligatorio")
    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    public User() {
    }

    public User(Long userId, String username, String email, String passwordHash,
                String status, LocalDateTime createdAt) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
        this.status = status;
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "User { userId=" + userId +
                ", username=" + username +
                ", email=" + email +
                ", status=" + status + " }";
    }
}

