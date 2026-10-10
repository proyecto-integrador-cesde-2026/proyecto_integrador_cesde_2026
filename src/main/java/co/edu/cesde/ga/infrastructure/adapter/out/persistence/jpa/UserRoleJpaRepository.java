package co.edu.cesde.ga.infrastructure.adapter.out.persistence.jpa;

import co.edu.cesde.ga.domain.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRoleJpaRepository extends JpaRepository<UserRole, Long> {
    boolean existsByUser_UserIdAndRole_RoleId(Long userId, Long roleId);

    List<UserRole> findByUser_UserId(Long userId);

    List<UserRole> findByRole_RoleId(Long roleId);
}
