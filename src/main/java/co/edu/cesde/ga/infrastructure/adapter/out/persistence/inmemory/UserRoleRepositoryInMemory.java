package co.edu.cesde.ga.infrastructure.adapter.out.persistence.inmemory;

import co.edu.cesde.ga.domain.model.UserRole;
import co.edu.cesde.ga.domain.model.port.out.UserRoleRepository;
import co.edu.cesde.ga.infrastructure.adapter.out.persistence.jpa.UserRoleJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRoleRepositoryInMemory implements UserRoleRepository {

    private final UserRoleJpaRepository userRoleJpaRepository;

    public UserRoleRepositoryInMemory(
            UserRoleJpaRepository userRoleJpaRepository) {
        this.userRoleJpaRepository = userRoleJpaRepository;
    }

    @Override
    public UserRole create(UserRole userRole) {
        return userRoleJpaRepository.save(userRole);
    }

    @Override
    public boolean existsByUserIdAndRoleId(Long userId, Long roleId) {
        return userRoleJpaRepository
                .existsByUser_UserIdAndRole_RoleId(userId, roleId);
    }

    @Override
    public List<UserRole> findByUserId(Long userId) {
        return userRoleJpaRepository.findByUser_UserId(userId);
    }

    @Override
    public List<UserRole> findByRoleId(Long roleId) {
        return userRoleJpaRepository.findByRole_RoleId(roleId);
    }

    @Override
    public List<UserRole> findAll() {
        return userRoleJpaRepository.findAll();
    }

    @Override
    public boolean delete(Long userId, Long roleId) {
        List<UserRole> userRoles =
                userRoleJpaRepository.findByUser_UserId(userId);

        for (UserRole userRole : userRoles) {
            if (userRole.getRole().getRoleId().equals(roleId)) {
                userRoleJpaRepository.delete(userRole);
                return true;
            }
        }

        return false;
    }

    @Override
    public int count() {
        return (int) userRoleJpaRepository.count();
    }
}
