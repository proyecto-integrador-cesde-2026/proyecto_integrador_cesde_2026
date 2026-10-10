package co.edu.cesde.ga.domain.model.port.out;

import co.edu.cesde.ga.domain.model.Groups;

import java.util.List;

public interface GroupRepository {

    Groups create(Groups group);

    boolean existsById(Long groupsId);

    List<Groups> findAll();

    Groups findById(Long groupId);


    List<Groups> findByProgramId(Long programId);

    boolean update(Groups group);

    boolean delete(Long groupId);

    int count();
}
