package co.edu.cesde.ga.infrastructure.adapter.out.persistence.jpa;

import co.edu.cesde.ga.domain.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentJpaRepository extends JpaRepository<Student, Long> {
    boolean existsByDocumentNumber(String documentNumber);

    Student findByDocumentNumber(String documentNumber);
}
