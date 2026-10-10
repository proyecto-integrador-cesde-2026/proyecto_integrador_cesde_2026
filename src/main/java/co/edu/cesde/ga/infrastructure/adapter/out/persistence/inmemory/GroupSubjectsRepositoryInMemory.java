package co.edu.cesde.ga.infrastructure.adapter.out.persistence.inmemory;

import co.edu.cesde.ga.domain.model.GroupSubjects;
import co.edu.cesde.ga.domain.model.port.out.GroupSubjectsRepository;
import co.edu.cesde.ga.infrastructure.adapter.out.persistence.jpa.GroupSubjectsJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GroupSubjectsRepositoryInMemory
        implements GroupSubjectsRepository {

    private final GroupSubjectsJpaRepository groupSubjectsJpaRepository;

    public GroupSubjectsRepositoryInMemory(
            GroupSubjectsJpaRepository groupSubjectsJpaRepository) {
        this.groupSubjectsJpaRepository = groupSubjectsJpaRepository;
    }

    @Override
    public GroupSubjects create(GroupSubjects groupSubject) {
        return groupSubjectsJpaRepository.save(groupSubject);
    }

    @Override
    public boolean exists(Long groupId, Long subjectId) {
        return groupSubjectsJpaRepository
                .existsByGroup_GroupIdAndSubject_SubjectId(groupId, subjectId);
    }

    @Override
    public GroupSubjects findByIds(Long groupId, Long subjectId) {
        return groupSubjectsJpaRepository
                .findByGroup_GroupIdAndSubject_SubjectId(groupId, subjectId)
                .orElse(null);
    }

    @Override
    public List<GroupSubjects> findAll() {
        return groupSubjectsJpaRepository.findAll();
    }

    @Override
    public boolean delete(Long groupId, Long subjectId) {
        GroupSubjects groupSubject = findByIds(groupId, subjectId);

        if (groupSubject == null) {
            return false;
        }

        groupSubjectsJpaRepository.delete(groupSubject);
        return true;
    }

    @Override
    public int count() {
        return (int) groupSubjectsJpaRepository.count();
    }

    @Override
    public boolean update(GroupSubjects groupSubject) {
        if (groupSubject == null
                || groupSubject.getGroupSubjectId() == null
                || !groupSubjectsJpaRepository.existsById(
                groupSubject.getGroupSubjectId())) {
            return false;
        }

        groupSubjectsJpaRepository.save(groupSubject);
        return true;
    }
}
