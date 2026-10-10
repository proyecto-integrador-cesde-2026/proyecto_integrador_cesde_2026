package co.edu.cesde.ga.infrastructure.adapter.out.persistence.jpa;

import co.edu.cesde.ga.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    User findByUsername(String username);

    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}
