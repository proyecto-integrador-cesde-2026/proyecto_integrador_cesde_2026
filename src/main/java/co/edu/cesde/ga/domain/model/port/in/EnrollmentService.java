package co.edu.cesde.ga.domain.model.port.in;

import co.edu.cesde.ga.domain.model.Enrollment;

import java.util.List;

public interface EnrollmentService {
    Enrollment create(Enrollment enrollment);
    Enrollment createFromIds(
            Long studentId,
            Long groupId,
            Long periodId,
            String status
    );


    boolean existsById(Long enrollmentId);

    Enrollment findById(Long enrollmentId);

    List<Enrollment> findAll();

    boolean delete(Long enrollmentId);

    boolean update(Enrollment enrollment);
}
