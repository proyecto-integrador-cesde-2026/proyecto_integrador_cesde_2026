package co.edu.cesde.ga.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "grades")
@Getter
@Setter
@ToString
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "grade_id")
    private Long gradeId;


    @NotNull(message = "El grupo de materia es obligatorio")
    @ManyToOne
    @JoinColumn(name = "group_subject_id", nullable = false)
    private GroupSubjects groupSubject;

    @NotNull(message = "El estudiante es obligatorio")
    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @Min(value = 0, message = "La nota mínima es 0")
    @Max(value = 5, message = "La nota máxima es 5")
    @Column(name = "final_score", nullable = false)
    private int finalScore;

    @NotBlank(message = "La observación no puede ser nula ni estar vacía")
    @Column(name = "observation", nullable = false)
    private String observation;

    public Grade() {
    }

    public Grade(Long gradeId,GroupSubjects groupSubject,Student student,
                 int finalScore, String observation) {
        this.gradeId = gradeId;
        this.groupSubject = groupSubject;
        this.student = student;
        this.finalScore = finalScore;
        this.observation = observation;
    }



    @Override
    public String toString() {
        return "Grade{" +
                "gradeId=" + gradeId +
                ", groupSubjectId=" + groupSubject +
                ", studentId=" + student +
                ", finalScore=" + finalScore +
                ", observation='" + observation + '\'' +
                '}';
    }
}