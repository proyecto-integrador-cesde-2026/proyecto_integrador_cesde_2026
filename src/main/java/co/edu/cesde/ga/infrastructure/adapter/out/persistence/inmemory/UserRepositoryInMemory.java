package co.edu.cesde.ga.infrastructure.adapter.out.persistence.inmemory;

import co.edu.cesde.ga.domain.model.User;
import co.edu.cesde.ga.domain.model.port.out.UserRepository;
import co.edu.cesde.ga.infrastructure.adapter.out.persistence.jpa.UserJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryInMemory implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    public UserRepositoryInMemory(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    public User create(User user) {
        return userJpaRepository.save(user);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userJpaRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userJpaRepository.existsByUsername(username);
    }

    @Override
    public User findByEmail(String email) {
        return userJpaRepository.findByEmail(email);
    }

    @Override
    public User findByUsername(String username) {
        return userJpaRepository.findByUsername(username);
    }

    @Override
    public List<User> findAll() {
        return userJpaRepository.findAll();
    }

    @Override
    public User findById(Long userId) {
        return userJpaRepository.findById(userId).orElse(null);
    }

    @Override
    public boolean delete(Long userId) {
        if (!userJpaRepository.existsById(userId)) {
            return false;
        }

        userJpaRepository.deleteById(userId);
        return true;
    }

    @Override
    public int count() {
        return (int) userJpaRepository.count();
    }

    @Override
    public boolean update(User user) {
        if (user == null || user.getUserId() == null
                || !userJpaRepository.existsById(user.getUserId())) {
            return false;
        }

        userJpaRepository.save(user);
        return true;
    }
}
