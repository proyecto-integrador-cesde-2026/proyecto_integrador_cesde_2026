package co.edu.cesde.ga.infrastructure.adapter.out.persistence.inmemory;

import co.edu.cesde.ga.domain.model.GroupSubjects;
import co.edu.cesde.ga.application.port.out.GroupSubjectsRepository;

import java.util.ArrayList;
import java.util.List;

public class GroupSubjectsRepositoryInMemory implements GroupSubjectsRepository {

    private List<GroupSubjects> groupSubjects;

    public GroupSubjectsRepositoryInMemory() {
        this.groupSubjects = new ArrayList<>();
    }

    @Override
    public GroupSubjects create(GroupSubjects groupSubject) {

        if (groupSubject == null) {
            return null;
        }

        if (exists(
                groupSubject.getGroup().getGroupId(),
                groupSubject.getSubject().getSubjectId())) {

            return null;
        }

        groupSubjects.add(groupSubject);
        return groupSubject;
    }

    @Override
    public boolean exists(Long groupId, Long subjectId) {
        return findByIds(groupId, subjectId) != null;
    }

    @Override
    public GroupSubjects findByIds(Long groupId, Long subjectId) {

        if (groupId == null || subjectId == null) {
            return null;
        }

        for (GroupSubjects gs : groupSubjects) {

            if (gs.getGroup().getGroupId().equals(groupId)
                    && gs.getSubject().getSubjectId().equals(subjectId)) {

                return gs;
            }
        }

        return null;
    }

    @Override
    public List<GroupSubjects> findAll() {
        return new ArrayList<>(groupSubjects);
    }

    @Override
    public boolean delete(Long groupId, Long subjectId) {

        GroupSubjects gs = findByIds(groupId, subjectId);

        if (gs == null) {
            return false;
        }

        groupSubjects.remove(gs);
        return true;
    }

    @Override
    public int count() {
        return groupSubjects.size();
    }

    @Override
    public boolean update(GroupSubjects updated) {

        if (updated == null) {
            return false;
        }

        for (int i = 0; i < groupSubjects.size(); i++) {

            GroupSubjects current = groupSubjects.get(i);

            if (current.getGroup().getGroupId().equals(
                    updated.getGroup().getGroupId())
                    && current.getSubject().getSubjectId().equals(
                    updated.getSubject().getSubjectId())) {

                groupSubjects.set(i, updated);
                return true;
            }
        }

        return false;
    }
}