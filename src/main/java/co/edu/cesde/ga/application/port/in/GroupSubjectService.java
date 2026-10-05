package co.edu.cesde.ga.application.port.in;

import co.edu.cesde.ga.domain.model.GroupSubjects;
import java.util.List;

public interface GroupSubjectService {
    GroupSubjects create(GroupSubjects groupSubject);
    boolean update(GroupSubjects groupSubject);
    boolean delete(Long groupId, Long subjectId);
    GroupSubjects findByIds(Long groupId, Long subjectId);
    boolean exists(Long groupId, Long subjectId);
    List<GroupSubjects> findAll();
}