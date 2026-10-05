package co.edu.cesde.ga.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "subject")
@Getter
@Setter
@ToString
public class Subject {

    @Id
    @NotNull
    @Column(name = "subject_id", nullable = false)
    private Long subjectId;

    @OneToMany(mappedBy = "subject")
    private List<GroupSubjects> groupSubjects;

    @NotBlank
    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @NotBlank
    @Column(name = "name", nullable = false)
    private String name;

    @Positive
    @Column(name = "credits", nullable = false)
    private int credits;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "program_id", nullable = false)
    private Programs program;

    public Subject() {
    }

    public Subject(Long subjectId, String code, String name, int credits, Programs program) {
        this.subjectId = subjectId;
        this.code = code;
        this.name = name;
        this.credits = credits;
        this.program = program;
    }



    @Override
    public String toString() {
        return "Subject { subjectId=" + subjectId +
                ", code=" + code +
                ", name=" + name +
                ", credits=" + credits +
                ", programId=" + program + " }";
    }
}