package co.edu.cesde.ga.application.service.impl;

import co.edu.cesde.ga.domain.model.Teacher;
import co.edu.cesde.ga.domain.model.exceptions.InvalidDataException;
import co.edu.cesde.ga.domain.model.exceptions.NotFoundException;
import co.edu.cesde.ga.domain.model.port.in.TeacherService;
import co.edu.cesde.ga.domain.model.port.out.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;

    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public Teacher create(Teacher teacher) {

        if (isInvalidTeacher(teacher)) {
            throw new InvalidDataException("Datos del profesor inválidos");
        }

        return teacherRepository.create(teacher);
    }

    @Override
    public Teacher update(Teacher teacher) {

        if (teacher == null || teacher.getTeacherId() == null
                || teacher.getTeacherId() <= 0) {
            throw new InvalidDataException("ID inválido");
        }

        if (!teacherRepository.existsById(teacher.getTeacherId())) {
            throw new NotFoundException("Profesor no encontrado");
        }

        if (isInvalidTeacher(teacher)) {
            throw new InvalidDataException("Datos inválidos");
        }

        return teacherRepository.update(teacher);
    }

    @Override
    public boolean delete(Long id) {

        if (id == null || id <= 0) {
            throw new InvalidDataException("ID inválido");
        }

        if (!teacherRepository.existsById(id)) {
            throw new NotFoundException("Profesor no encontrado");
        }

        return teacherRepository.delete(id);
    }

    @Override
    public Teacher findById(Long id) {

        if (id == null || id <= 0) {
            throw new InvalidDataException("ID inválido");
        }

        Teacher teacher = teacherRepository.findById(id);

        if (teacher == null) {
            throw new NotFoundException("Profesor no encontrado");
        }

        return teacher;
    }

    @Override
    public Teacher findByName(String name) {

        if (name == null || name.isBlank()) {
            throw new InvalidDataException("Nombre inválido");
        }

        Teacher teacher = teacherRepository.findByName(name);

        if (teacher == null) {
            throw new NotFoundException("Profesor no encontrado");
        }

        return teacher;
    }

    @Override
    public boolean existsById(Long id) {

        if (id == null || id <= 0) {
            return false;
        }

        return teacherRepository.existsById(id);
    }

    @Override
    public List<Teacher> findAll() {
        return teacherRepository.findAll();
    }

    private boolean isInvalidTeacher(Teacher teacher) {

        return teacher == null
                || !isNotBlank(teacher.getDocumentNumber())
                || !isNotBlank(teacher.getFirstName())
                || !isNotBlank(teacher.getLastName())
                || !isNotBlank(teacher.getStatus());
    }

    private boolean isNotBlank(String value) {
        return value != null && !value.trim().isEmpty();
    }
}
