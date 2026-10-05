package co.edu.cesde.ga.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "programs")
@Getter
@Setter
@ToString

public class Programs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long programId;

    @OneToMany(mappedBy = "program")
    private List<Groups> groups;

    @OneToMany(mappedBy = "program")
    private List<Subject> subjects;

    @NotBlank
    private String code;

    @NotBlank
    private String name;

    public Programs() {
    }

    public Programs(Long programId, String code, String name) {
        this.programId = programId;
        this.code = code;
        this.name = name;
    }



    @Override
    public String toString() {
        return "Program { programId=" + programId +
                ", code=" + code +
                ", name=" + name + " }";
    }
}