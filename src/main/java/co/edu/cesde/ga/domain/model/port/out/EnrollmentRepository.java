package co.edu.cesde.ga.domain.model.port.out;


import co.edu.cesde.ga.domain.model.Enrollment;

import java.util.List;

public interface EnrollmentRepository {

    Enrollment create(Enrollment enrollment);

    boolean existsById(Long enrollmentId);

    Enrollment findById(Long enrollmentId);

    List<Enrollment> findAll();

    boolean delete(Long enrollmentId);

    int count();

    boolean update(Enrollment enrollment);
}