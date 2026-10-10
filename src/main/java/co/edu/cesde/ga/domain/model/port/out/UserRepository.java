package co.edu.cesde.ga.domain.model.port.out;

import co.edu.cesde.ga.domain.model.User;
import java.util.List;

public interface UserRepository {

    User create(User user);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    User findByEmail(String email);

    User findByUsername(String username);

    List<User> findAll();

    User findById(Long userId);

    boolean delete(Long userId);

    int count();

    boolean update(User user);
}