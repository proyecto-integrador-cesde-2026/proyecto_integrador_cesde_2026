package co.edu.cesde.ga.infrastructure.adapter.out.persistence.jpa;

import co.edu.cesde.ga.domain.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentJpaRepository extends JpaRepository<Enrollment, Long> {
}
