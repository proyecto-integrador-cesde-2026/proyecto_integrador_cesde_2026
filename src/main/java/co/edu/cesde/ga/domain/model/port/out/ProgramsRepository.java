package co.edu.cesde.ga.domain.model.port.out;

import co.edu.cesde.ga.domain.model.Programs;

import java.util.List;


public interface ProgramsRepository {

    Programs create(Programs program);

    List<Programs> findAll();

    Programs findById(Long programId);

    Programs findByCode(String code);

    boolean update(Programs program);

    boolean delete(Long programId);

    boolean existsByCode(String code);

    int count();
}