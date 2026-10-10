package co.edu.cesde.ga.infrastructure.adapter.out.persistence.jpa;

import co.edu.cesde.ga.domain.model.Groups;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupsJpaRepository extends JpaRepository<Groups, Long> {
    List<Groups> findByProgram_ProgramId(Long programId);
}
