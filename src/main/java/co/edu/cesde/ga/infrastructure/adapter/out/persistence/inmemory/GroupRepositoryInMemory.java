package co.edu.cesde.ga.infrastructure.adapter.out.persistence.inmemory;

import co.edu.cesde.ga.domain.model.Groups;
import co.edu.cesde.ga.domain.model.port.out.GroupRepository;
import co.edu.cesde.ga.infrastructure.adapter.out.persistence.jpa.GroupsJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GroupRepositoryInMemory implements GroupRepository {

    private final GroupsJpaRepository groupsJpaRepository;

    public GroupRepositoryInMemory(GroupsJpaRepository groupsJpaRepository) {
        this.groupsJpaRepository = groupsJpaRepository;
    }

    @Override
    public Groups create(Groups group) {
        return groupsJpaRepository.save(group);
    }

    @Override
    public boolean existsById(Long groupsId) {
        return groupsJpaRepository.existsById(groupsId);
    }

    @Override
    public List<Groups> findAll() {
        return groupsJpaRepository.findAll();
    }

    @Override
    public Groups findById(Long groupId) {
        return groupsJpaRepository.findById(groupId).orElse(null);
    }

    @Override
    public List<Groups> findByProgramId(Long programId) {
        return groupsJpaRepository.findByProgram_ProgramId(programId);
    }

    @Override
    public boolean update(Groups group) {
        if (group == null || group.getGroupId() == null
                || !groupsJpaRepository.existsById(group.getGroupId())) {
            return false;
        }

        groupsJpaRepository.save(group);
        return true;
    }

    @Override
    public boolean delete(Long groupId) {
        if (!groupsJpaRepository.existsById(groupId)) {
            return false;
        }

        groupsJpaRepository.deleteById(groupId);
        return true;
    }

    @Override
    public int count() {
        return (int) groupsJpaRepository.count();
    }
}
