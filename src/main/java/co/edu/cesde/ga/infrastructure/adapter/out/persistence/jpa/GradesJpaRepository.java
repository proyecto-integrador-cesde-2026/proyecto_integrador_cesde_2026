package co.edu.cesde.ga.infrastructure.adapter.out.persistence.jpa;

import co.edu.cesde.ga.domain.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradesJpaRepository extends JpaRepository<Grade, Long> {
}
