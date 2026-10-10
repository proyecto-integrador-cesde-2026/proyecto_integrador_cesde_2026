package co.edu.cesde.ga.application.service.impl;

import co.edu.cesde.ga.domain.model.exceptions.DuplicateException;
import co.edu.cesde.ga.domain.model.exceptions.InvalidDataException;
import co.edu.cesde.ga.domain.model.exceptions.NotFoundException;
import co.edu.cesde.ga.domain.model.User;
import co.edu.cesde.ga.domain.model.port.out.UserRepository;
import co.edu.cesde.ga.domain.model.port.in.UserService;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(User user) {

        if (user == null) {
            throw new InvalidDataException("El usuario no puede ser nulo");
        }

        if (user.getCreatedAt() == null) {
            user.setCreatedAt(LocalDateTime.now());
        }

        if (isInvalidUser(user)) {
            throw new InvalidDataException("Datos inválidos");
        }

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new DuplicateException("Ya existe un usuario con ese email");
        }

        if (userRepository.existsByUsername(user.getUsername())) {
            throw new DuplicateException("Ya existe un usuario con ese username");
        }

        return userRepository.create(user);
    }

    @Override
    public boolean update(User user) {

        if (user == null || user.getUserId() == null || user.getUserId() <= 0L) {
            throw new InvalidDataException("ID inválido");
        }

        if (userRepository.findById(user.getUserId()) == null) {
            throw new NotFoundException("Usuario no encontrado");
        }

        if (isInvalidUser(user)) {
            throw new InvalidDataException("Datos inválidos");
        }

        return userRepository.update(user);
    }

    @Override
    public boolean delete(Long userId) {

        if (userId == null || userId <= 0L) {
            throw new InvalidDataException("ID inválido");
        }

        if (userRepository.findById(userId) == null) {
            throw new NotFoundException("Usuario no encontrado");
        }

        return userRepository.delete(userId);
    }

    @Override
    public User findById(Long userId) {

        if (userId == null || userId <= 0L) {
            throw new InvalidDataException("ID inválido");
        }

        User user = userRepository.findById(userId);

        if (user == null) {
            throw new NotFoundException("Usuario no encontrado");
        }

        return user;
    }

    @Override
    public User findByEmail(String email) {

        if (!isNotBlank(email)) {
            throw new InvalidDataException("Email inválido");
        }

        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new NotFoundException("Usuario no encontrado");
        }

        return user;
    }

    @Override
    public User findByUsername(String username) {

        if (!isNotBlank(username)) {
            throw new InvalidDataException("Username inválido");
        }

        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new NotFoundException("Usuario no encontrado");
        }

        return user;
    }

    @Override
    public boolean existsByEmail(String email) {

        if (!isNotBlank(email)) {
            throw new InvalidDataException("Email inválido");
        }

        return userRepository.existsByEmail(email);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    private boolean isInvalidUser(User user) {
        return user == null
                || !isNotBlank(user.getUsername())
                || !isNotBlank(user.getEmail())
                || !isNotBlank(user.getPasswordHash())
                || !isNotBlank(user.getStatus());
    }

    private boolean isNotBlank(String value) {
        return value != null && !value.trim().isEmpty();
    }
}