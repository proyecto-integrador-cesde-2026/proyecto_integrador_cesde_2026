package co.edu.cesde.ga.domain.model.port.in;

import co.edu.cesde.ga.domain.model.Role;
import java.util.List;

public interface RoleService {
    Role create(Role role);
    boolean update(Role role);
    boolean delete(long roleId);
    Role findById(long roleId);
    Role findByName(String name);
    boolean existsByName(String name);
    List<Role> findAll();
}