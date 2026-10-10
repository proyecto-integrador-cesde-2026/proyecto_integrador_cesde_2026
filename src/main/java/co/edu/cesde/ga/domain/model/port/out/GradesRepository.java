package co.edu.cesde.ga.domain.model.port.out;

import co.edu.cesde.ga.domain.model.Grade;
import java.util.List;

public interface GradesRepository {

    Grade create(Grade grade);

    boolean existsById(long gradeId);

    Grade findById(long gradeId);

    List<Grade> findAll();

    boolean delete(long gradeId);

    int count();

    boolean update(Grade grade);
}
