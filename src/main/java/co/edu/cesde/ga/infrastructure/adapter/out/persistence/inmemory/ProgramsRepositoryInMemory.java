package co.edu.cesde.ga.infrastructure.adapter.out.persistence.inmemory;

import co.edu.cesde.ga.domain.model.Programs;
import co.edu.cesde.ga.domain.model.port.out.ProgramsRepository;
import co.edu.cesde.ga.infrastructure.adapter.out.persistence.jpa.ProgramsJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProgramsRepositoryInMemory implements ProgramsRepository {

    private final ProgramsJpaRepository programsJpaRepository;

    public ProgramsRepositoryInMemory(ProgramsJpaRepository programsJpaRepository) {
        this.programsJpaRepository = programsJpaRepository;
    }

    @Override
    public Programs create(Programs program) {
        return programsJpaRepository.save(program);
    }

    @Override
    public List<Programs> findAll() {
        return programsJpaRepository.findAll();
    }

    @Override
    public Programs findById(Long programId) {
        return programsJpaRepository.findById(programId).orElse(null);
    }

    @Override
    public Programs findByCode(String code) {
        return programsJpaRepository.findByCode(code);
    }

    @Override
    public boolean update(Programs program) {
        if (program == null || program.getProgramId() == null
                || !programsJpaRepository.existsById(program.getProgramId())) {
            return false;
        }

        programsJpaRepository.save(program);
        return true;
    }

    @Override
    public boolean delete(Long programId) {
        if (!programsJpaRepository.existsById(programId)) {
            return false;
        }

        programsJpaRepository.deleteById(programId);
        return true;
    }

    @Override
    public boolean existsByCode(String code) {
        return programsJpaRepository.existsByCode(code);
    }

    @Override
    public int count() {
        return (int) programsJpaRepository.count();
    }
}