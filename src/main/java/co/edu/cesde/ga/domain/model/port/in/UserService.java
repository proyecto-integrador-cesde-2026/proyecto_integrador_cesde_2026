package co.edu.cesde.ga.domain.model.port.in;

import co.edu.cesde.ga.domain.model.User;
import java.util.List;

public interface UserService {
    User create(User user);
    boolean update(User user);
    boolean delete(Long userId);
    User findById(Long userId);
    User findByEmail(String email);
    User findByUsername(String username);
    boolean existsByEmail(String email);
    List<User> findAll();
}