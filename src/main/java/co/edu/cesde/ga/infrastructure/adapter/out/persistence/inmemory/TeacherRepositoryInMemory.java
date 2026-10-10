package co.edu.cesde.ga.infrastructure.adapter.out.persistence.inmemory;

import co.edu.cesde.ga.domain.model.Teacher;
import co.edu.cesde.ga.domain.model.port.out.TeacherRepository;
import co.edu.cesde.ga.infrastructure.adapter.out.persistence.jpa.TeacherJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TeacherRepositoryInMemory implements TeacherRepository {

    private final TeacherJpaRepository teacherJpaRepository;

    public TeacherRepositoryInMemory(TeacherJpaRepository teacherJpaRepository) {
        this.teacherJpaRepository = teacherJpaRepository;
    }

    @Override
    public Teacher create(Teacher teacher) {
        return teacherJpaRepository.save(teacher);
    }

    @Override
    public Teacher update(Teacher teacher) {
        if (teacher == null || !teacherJpaRepository.existsById(teacher.getTeacherId())) {
            return null;
        }

        return teacherJpaRepository.save(teacher);
    }

    @Override
    public List<Teacher> findAll() {
        return teacherJpaRepository.findAll();
    }

    @Override
    public Teacher findById(Long id) {
        return teacherJpaRepository.findById(id).orElse(null);
    }

    @Override
    public Teacher findByName(String name) {
        return teacherJpaRepository.findByFirstNameContainingIgnoreCase(name);
    }

    @Override
    public boolean existsById(Long id) {
        return teacherJpaRepository.existsById(id);
    }

    @Override
    public boolean delete(Long id) {
        if (!teacherJpaRepository.existsById(id)) {
            return false;
        }

        teacherJpaRepository.deleteById(id);
        return true;
    }

    @Override
    public int count() {
        return (int) teacherJpaRepository.count();
    }
}

