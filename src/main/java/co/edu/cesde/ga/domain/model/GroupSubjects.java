package co.edu.cesde.ga.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "group_subjects")
@Getter
@Setter
@ToString
public class GroupSubjects {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_subject_id")
    private Long groupSubjectId;

    // Muchos registros de grupo-materia pertenecen a un grupo
    @NotNull(message = "El grupo es obligatorio")
    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private Groups group;

    // Muchos registros de grupo-materia pertenecen a una materia
    @NotNull(message = "La materia es obligatoria")
    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    // Muchos registros de grupo-materia pertenecen a un profesor
    @NotNull(message = "El profesor es obligatorio")
    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    private Teacher teacher;

    // Un grupo-materia puede tener muchas notas
    @OneToMany(mappedBy = "groupSubject")
    private List<Grade> grades;

    public GroupSubjects() {
    }

    public GroupSubjects(Long groupSubjectId, Groups group,
                         Subject subject, Teacher teacher) {
        this.groupSubjectId = groupSubjectId;
        this.group = group;
        this.subject = subject;
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return "GroupSubjects{" +
                "groupSubjectId=" + groupSubjectId +
                ", groupId=" + group +
                ", subjectId=" + subject +
                ", teacherId=" + teacher +
                '}';
    }
}
