package co.edu.cesde.ga.domain.model.port.in;

import co.edu.cesde.ga.domain.model.Teacher;
import java.util.List;

public interface TeacherService {
    Teacher create(Teacher teacher);
    Teacher update(Teacher teacher);
    boolean delete(Long id);
    Teacher findById(Long id);
    Teacher findByName(String name);
    boolean existsById(Long id);
    List<Teacher> findAll();
}