package co.edu.cesde.ga.domain.model.port.out;

import co.edu.cesde.ga.domain.model.Period;
import java.util.List;

public interface PeriodRepository {

    Period create(Period period);

    List<Period> findAll();

    Period findById(Long periodId);

    boolean delete(Long periodId);

    int count();

    boolean update(Period period);

    boolean existsById(Long periodId);
}