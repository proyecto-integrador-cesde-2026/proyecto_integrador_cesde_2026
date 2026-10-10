package co.edu.cesde.ga.domain.model.port.out;

import co.edu.cesde.ga.domain.model.Teacher;
import java.util.List;

public interface TeacherRepository {

    // Crear profesor
    Teacher create(Teacher teacher);

    // Actualizar profesor
    Teacher update(Teacher teacher);

    // Listar todos los profesores
    List<Teacher> findAll();

    // Buscar por id
    Teacher findById(Long id);

    // Buscar por nombre
    Teacher findByName(String name);

    // Validar si existe por id
    boolean existsById(Long id);

    // Eliminar por id
    boolean delete(Long id);

    // Contar registros
    int count();
}