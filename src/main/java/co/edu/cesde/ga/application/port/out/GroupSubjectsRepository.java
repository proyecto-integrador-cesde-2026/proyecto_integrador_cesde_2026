package co.edu.cesde.ga.application.port.out;

import co.edu.cesde.ga.domain.model.GroupSubjects;
import java.util.List;

public interface GroupSubjectsRepository {

    GroupSubjects create(GroupSubjects groupSubject);

    boolean exists(Long groupId, Long subjectId);

    GroupSubjects findByIds(Long groupId, Long subjectId);

    List<GroupSubjects> findAll();

    boolean delete(Long groupId, Long subjectId);

    int count();

    boolean update(GroupSubjects groupSubject);
}