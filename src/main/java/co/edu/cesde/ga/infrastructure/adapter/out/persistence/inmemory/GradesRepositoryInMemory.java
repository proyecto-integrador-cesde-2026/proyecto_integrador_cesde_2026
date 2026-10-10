package co.edu.cesde.ga.infrastructure.adapter.out.persistence.inmemory;

import co.edu.cesde.ga.domain.model.Grade;
import co.edu.cesde.ga.domain.model.port.out.GradesRepository;
import co.edu.cesde.ga.infrastructure.adapter.out.persistence.jpa.GradesJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GradesRepositoryInMemory implements GradesRepository {

    private final GradesJpaRepository gradesJpaRepository;

    public GradesRepositoryInMemory(
            GradesJpaRepository gradesJpaRepository) {
        this.gradesJpaRepository = gradesJpaRepository;
    }

    @Override
    public Grade create(Grade grade) {
        return gradesJpaRepository.save(grade);
    }

    @Override
    public boolean existsById(long gradeId) {
        return gradesJpaRepository.existsById(gradeId);
    }

    @Override
    public Grade findById(long gradeId) {
        return gradesJpaRepository.findById(gradeId).orElse(null);
    }

    @Override
    public List<Grade> findAll() {
        return gradesJpaRepository.findAll();
    }

    @Override
    public boolean delete(long gradeId) {
        if (!gradesJpaRepository.existsById(gradeId)) {
            return false;
        }

        gradesJpaRepository.deleteById(gradeId);
        return true;
    }

    @Override
    public int count() {
        return (int) gradesJpaRepository.count();
    }

    @Override
    public boolean update(Grade grade) {
        if (grade == null
                || !gradesJpaRepository.existsById(grade.getGradeId())) {
            return false;
        }

        gradesJpaRepository.save(grade);
        return true;
    }
}

