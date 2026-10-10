package co.edu.cesde.ga.infrastructure.adapter.out.persistence.jpa;

import co.edu.cesde.ga.domain.model.Programs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgramsJpaRepository extends JpaRepository<Programs, Long> {
    Programs findByCode(String code);

    boolean existsByCode(String code);
}
