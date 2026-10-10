package co.edu.cesde.ga.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "groups")
@Getter
@Setter
@ToString
public class Groups {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id")
    private Long groupId;

    // Un grupo puede tener muchas matrículas
    @OneToMany(mappedBy = "group")
    private List<Enrollment> enrollments;

    // Un grupo puede tener muchas materias
    @OneToMany(mappedBy = "group")
    private List<GroupSubjects> groupSubjects;

    @NotBlank(message = "El código del grupo no puede ser nulo ni estar vacío")
    @Column(name = "code", nullable = false)
    private String code;

    // Muchos grupos pertenecen a un programa
    @NotNull(message = "El programa es obligatorio")
    @ManyToOne
    @JoinColumn(name = "program_id", nullable = false)
    private Programs program;

    // Muchos grupos pertenecen a un período
    @NotNull(message = "El período es obligatorio")
    @ManyToOne
    @JoinColumn(name = "period_id", nullable = false)
    private Period period;

    @NotBlank(message = "La jornada no puede ser nula ni estar vacía")
    @Column(name = "shift", nullable = false)
    private String shift;

    public Groups() {
    }

    public Groups(Long groupId, String code, Programs program,
                  Period period, String shift) {
        this.groupId = groupId;
        this.code = code;
        this.program = program;
        this.period = period;
        this.shift = shift;
    }

    @Override
    public String toString() {
        return "Groups{" +
                "groupId=" + groupId +
                ", code='" + code + '\'' +
                ", programId=" + program +
                ", periodId=" + period +
                ", shift='" + shift + '\'' +
                '}';
    }
}
