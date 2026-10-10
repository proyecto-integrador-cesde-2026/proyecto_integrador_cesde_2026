package co.edu.cesde.ga.infrastructure.adapter.out.persistence.inmemory;

import co.edu.cesde.ga.domain.model.Role;
import co.edu.cesde.ga.domain.model.port.out.RoleRepository;
import co.edu.cesde.ga.infrastructure.adapter.out.persistence.jpa.RoleJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleRepositoryInMemory implements RoleRepository {

    private final RoleJpaRepository roleJpaRepository;

    public RoleRepositoryInMemory(RoleJpaRepository roleJpaRepository) {
        this.roleJpaRepository = roleJpaRepository;
    }

    @Override
    public Role create(Role role) {
        return roleJpaRepository.save(role);
    }

    @Override
    public boolean existsByName(String name) {
        return roleJpaRepository.existsByName(name);
    }

    @Override
    public Role findByName(String name) {
        return roleJpaRepository.findByName(name);
    }

    @Override
    public List<Role> findAll() {
        return roleJpaRepository.findAll();
    }

    @Override
    public Role findById(long roleId) {
        return roleJpaRepository.findById(roleId).orElse(null);
    }

    @Override
    public boolean delete(long roleId) {
        if (!roleJpaRepository.existsById(roleId)) {
            return false;
        }

        roleJpaRepository.deleteById(roleId);
        return true;
    }

    @Override
    public int count() {
        return (int) roleJpaRepository.count();
    }

    @Override
    public boolean update(Role role) {
        if (role == null || !roleJpaRepository.existsById(role.getRoleId())) {
            return false;
        }

        roleJpaRepository.save(role);
        return true;
    }
}

