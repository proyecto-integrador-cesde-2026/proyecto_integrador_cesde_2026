
package co.edu.cesde.ga.domain.model.port.in;

import co.edu.cesde.ga.domain.model.Grade;
import java.util.List;

public interface GradesService {
    Grade create(Grade grade);
    boolean update(Grade grade);
    boolean delete(long gradeId);
    Grade findById(long gradeId);
    List<Grade> findAll();
}