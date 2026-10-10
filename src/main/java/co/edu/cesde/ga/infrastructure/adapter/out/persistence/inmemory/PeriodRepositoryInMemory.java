package co.edu.cesde.ga.infrastructure.adapter.out.persistence.inmemory;

import co.edu.cesde.ga.domain.model.Period;
import co.edu.cesde.ga.domain.model.port.out.PeriodRepository;
import co.edu.cesde.ga.infrastructure.adapter.out.persistence.jpa.PeriodJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PeriodRepositoryInMemory implements PeriodRepository {

    private final PeriodJpaRepository periodJpaRepository;

    public PeriodRepositoryInMemory(PeriodJpaRepository periodJpaRepository) {
        this.periodJpaRepository = periodJpaRepository;
    }

    @Override
    public Period create(Period period) {
        return periodJpaRepository.save(period);
    }

    @Override
    public List<Period> findAll() {
        return periodJpaRepository.findAll();
    }

    @Override
    public Period findById(Long periodId) {
        return periodJpaRepository.findById(periodId).orElse(null);
    }

    @Override
    public boolean delete(Long periodId) {
        if (!periodJpaRepository.existsById(periodId)) {
            return false;
        }

        periodJpaRepository.deleteById(periodId);
        return true;
    }

    @Override
    public int count() {
        return (int) periodJpaRepository.count();
    }

    @Override
    public boolean update(Period period) {
        if (period == null || period.getPeriodId() == null
                || !periodJpaRepository.existsById(period.getPeriodId())) {
            return false;
        }

        periodJpaRepository.save(period);
        return true;
    }

    @Override
    public boolean existsById(Long periodId) {
        return periodJpaRepository.existsById(periodId);
    }
}
