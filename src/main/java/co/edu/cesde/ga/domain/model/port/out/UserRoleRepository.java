package co.edu.cesde.ga.domain.model.port.out;

import co.edu.cesde.ga.domain.model.UserRole;
import java.util.List;

public interface UserRoleRepository {

    UserRole create(UserRole userRole);

    boolean existsByUserIdAndRoleId(Long userId, Long roleId);

    List<UserRole> findByUserId(Long userId);

    List<UserRole> findByRoleId(Long roleId);

    List<UserRole> findAll();

    boolean delete(Long userId, Long roleId);

    int count();
}