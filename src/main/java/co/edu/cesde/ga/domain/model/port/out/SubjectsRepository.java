package co.edu.cesde.ga.domain.model.port.out;

import co.edu.cesde.ga.domain.model.Subject;
import java.util.List;

public interface SubjectsRepository {

    Subject create(Subject subject);

    boolean existsByCode(String code);

    boolean existsById(Long subjectId);

    Subject findByCode(String code);

    List<Subject> findAll();

    boolean delete(String code);

    int count();

    boolean update(Subject subject);
}
