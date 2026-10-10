package co.edu.cesde.ga.domain.model.port.in;

import co.edu.cesde.ga.domain.model.UserRole;
import java.util.List;

public interface UserRoleService {
    UserRole create(UserRole userRole);
    boolean delete(Long userId, Long roleId);
    boolean existsByUserIdAndRoleId(Long userId, Long roleId);
    List<UserRole> findByUserId(Long userId);
    List<UserRole> findByRoleId(Long roleId);
    List<UserRole> findAll();
}