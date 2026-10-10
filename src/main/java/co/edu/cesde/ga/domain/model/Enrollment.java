package co.edu.cesde.ga.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Table(name = "enrollments")
@Getter
@Setter
@ToString
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "enrollment_id_pk")
    private Long enrollmentId;

    // Relación: muchas matrículas pertenecen a un estudiante
    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    // Relación: muchas matrículas pertenecen a un grupo
    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private Groups group;

    @ManyToOne
    @JoinColumn(name = "period_id", nullable = false)
    private Period period;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "enrollment_created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "enrollment_updated_at")
    private LocalDateTime updatedAt;

    public Enrollment() {
    }

    public Enrollment(Long enrollmentId, Student student, Groups group,
                      Period period, String status) {
        this.enrollmentId = enrollmentId;
        this.student = student;
        this.group = group;
        this.period = period;
        this.status = status;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        createdAt = now;
        updatedAt = now;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "=== ENROLLMENT INFO ===\n" +
                "ID: " + enrollmentId + "\n" +
                "Student ID: " + student + "\n" +
                "Group ID: " + group + "\n" +
                "Period ID: " + period + "\n" +
                "Estado: " + status;
    }
}
