package co.edu.cesde.ga.domain.model.port.in;

import co.edu.cesde.ga.domain.model.Period;
import java.util.List;

public interface PeriodService {
    Period create(Period period);

    List<Period> findAll();

    Period findById(Long periodId);

    boolean update(Period period);

    boolean delete(Long periodId);

    boolean existsById(Long periodId);
}
