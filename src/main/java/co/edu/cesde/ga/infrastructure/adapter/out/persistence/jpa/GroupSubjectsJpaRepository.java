package co.edu.cesde.ga.infrastructure.adapter.out.persistence.jpa;

import co.edu.cesde.ga.domain.model.GroupSubjects;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GroupSubjectsJpaRepository
        extends JpaRepository<GroupSubjects, Long> {

    boolean existsByGroup_GroupIdAndSubject_SubjectId(
            Long groupId, Long subjectId);

    Optional<GroupSubjects> findByGroup_GroupIdAndSubject_SubjectId(
            Long groupId, Long subjectId);

    List<GroupSubjects> findByGroup_GroupId(Long groupId);

    List<GroupSubjects> findBySubject_SubjectId(Long subjectId);
}

