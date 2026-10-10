package co.edu.cesde.ga.domain.model.port.out;

import co.edu.cesde.ga.domain.model.Role;
import java.util.List;

public interface RoleRepository {

    Role create(Role role);

    boolean existsByName(String name);

    Role findByName(String name);

    List<Role> findAll();

    Role findById(long roleId);

    boolean delete(long roleId);

    int count();

    boolean update(Role role);
}