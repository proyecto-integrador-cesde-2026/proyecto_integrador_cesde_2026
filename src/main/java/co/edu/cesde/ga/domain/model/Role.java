package co.edu.cesde.ga.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "role")
@Getter
@Setter
@ToString
public class Role {

    @Id
    @NotNull
    @Column(name = "role_id", nullable = false)
    private Long roleId;

    // Un rol puede tener muchos registros en UserRole
    @OneToMany(mappedBy = "role")
    private List<UserRole> userRoles;

    @NotBlank
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @NotBlank
    @Column(name = "description", nullable = false)
    private String description;

    public Role() {
    }

    public Role(Long roleId, String name, String description) {
        this.roleId = roleId;
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Role { roleId=" + roleId +
                ", name=" + name +
                ", description=" + description + " }";
    }
}
