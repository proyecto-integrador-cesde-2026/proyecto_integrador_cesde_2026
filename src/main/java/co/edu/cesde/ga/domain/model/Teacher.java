package co.edu.cesde.ga.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "teacher")
@Getter
@Setter
@ToString
public class Teacher extends Person {

    @Id
    @Column(name = "teacher_id", nullable = false)
    private Long teacherId;

    // Un profesor puede tener muchos grupos-materia
    @OneToMany(mappedBy = "teacher")
    private List<GroupSubjects> groupSubjects;

    public Teacher() {
    }

    public Teacher(Long userId, String code, String documentNumber,
                   String firstName, String lastName, String status) {
        super(userId, code, documentNumber, firstName, lastName, status);
    }

    @Override
    public String getRole() {
        return "=== TEACHER INFO ===\n" +
                "ID: " + getUserId() + "\n" +
                "Código: " + getCode() + "\n" +
                "Documento: " + getDocumentNumber() + "\n" +
                "Nombre: " + getFirstName() + " " + getLastName() + "\n" +
                "Estado: " + getStatus();
    }
}
