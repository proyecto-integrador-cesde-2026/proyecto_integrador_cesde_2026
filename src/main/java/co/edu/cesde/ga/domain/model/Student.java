package co.edu.cesde.ga.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.List;

@Entity
@Table(name = "students")
@Getter
@Setter
@ToString
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Long studentId;

    @OneToMany(mappedBy = "student")
    private List<Enrollment> enrollments;

    @OneToMany(mappedBy = "student")
    private List<Grade> grades;

    @Column(nullable = false, unique = true)
    private String code;

    @Column(name = "document_number", nullable = false, unique = true)
    private String documentNumber;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String status;

    @Column(name = "birth_date")
    private String birthDate;

    public Student() {
    }

    public Student(Long studentId, String code, String documentNumber,
                   String firstName, String lastName, String status,
                   String birthDate) {
        this.studentId = studentId;
        this.code = code;
        this.documentNumber = documentNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.status = status;
        this.birthDate = birthDate;
    }



    @Override
    public String toString() {
        return "=== STUDENT INFO ===\n" +
                "ID: " + studentId + "\n" +
                "Código: " + code + "\n" +
                "Documento: " + documentNumber + "\n" +
                "Nombre: " + firstName + " " + lastName + "\n" +
                "Fecha Nacimiento: " + birthDate + "\n" +
                "Estado: " + status;
    }
}