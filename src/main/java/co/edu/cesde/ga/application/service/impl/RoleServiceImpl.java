package co.edu.cesde.ga.application.service.impl;

import co.edu.cesde.ga.domain.model.exceptions.DuplicateException;
import co.edu.cesde.ga.domain.model.exceptions.InvalidDataException;
import co.edu.cesde.ga.domain.model.exceptions.NotFoundException;
import co.edu.cesde.ga.domain.model.Role;
import co.edu.cesde.ga.domain.model.port.out.RoleRepository;
import co.edu.cesde.ga.domain.model.port.in.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    @Override
    public Role create(Role role) {

        if (role == null) {
            throw new InvalidDataException("El rol no puede ser nulo");
        }

        if (isInvalidRole(role)) {
            throw new InvalidDataException("Datos inválidos");
        }

        if (roleRepository.existsByName(role.getName())) {
            throw new DuplicateException("Ya existe un rol con ese nombre");
        }

        return roleRepository.create(role);
    }

    @Override
    public boolean update(Role role) {

        if (role == null || role.getRoleId() <= 0L) {
            throw new InvalidDataException("ID inválido");
        }

        if (roleRepository.findById(role.getRoleId()) == null) {
            throw new NotFoundException("Rol no encontrado");
        }

        if (isInvalidRole(role)) {
            throw new InvalidDataException("Datos inválidos");
        }

        return roleRepository.update(role);
    }


    @Override
    public boolean delete(long roleId) {

        if (roleId <= 0L) {
            throw new InvalidDataException("ID inválido");
        }

        if (roleRepository.findById(roleId) == null) {
            throw new NotFoundException("Rol no encontrado");
        }

        return roleRepository.delete(roleId);
    }


    @Override
    public Role findById(long roleId) {

        if (roleId <= 0L) {
            throw new InvalidDataException("ID inválido");
        }

        Role role = roleRepository.findById(roleId);

        if (role == null) {
            throw new NotFoundException("Rol no encontrado");
        }

        return role;
    }

    @Override
    public Role findByName(String name) {

        if (!isNotBlank(name)) {
            throw new InvalidDataException("Nombre inválido");
        }

        Role role = roleRepository.findByName(name);

        if (role == null) {
            throw new NotFoundException("Rol no encontrado");
        }

        return role;
    }


    @Override
    public boolean existsByName(String name) {

        if (!isNotBlank(name)) {
            throw new InvalidDataException("Nombre inválido");
        }

        return roleRepository.existsByName(name);
    }
    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    private boolean isInvalidRole(Role role) {

        return role == null
                || role.getRoleId() <= 0L
                || !isNotBlank(role.getName())
                || !isNotBlank(role.getDescription());
    }

    private boolean isNotBlank(String value) {
        return value != null && !value.trim().isEmpty();
    }
}