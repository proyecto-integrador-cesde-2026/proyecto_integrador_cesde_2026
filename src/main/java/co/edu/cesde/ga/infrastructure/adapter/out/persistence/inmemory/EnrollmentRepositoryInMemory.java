package co.edu.cesde.ga.infrastructure.adapter.out.persistence.inmemory;

import co.edu.cesde.ga.domain.model.Enrollment;
import co.edu.cesde.ga.domain.model.port.out.EnrollmentRepository;
import co.edu.cesde.ga.infrastructure.adapter.out.persistence.jpa.EnrollmentJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EnrollmentRepositoryInMemory implements EnrollmentRepository {

    private final EnrollmentJpaRepository enrollmentJpaRepository;

    public EnrollmentRepositoryInMemory(
            EnrollmentJpaRepository enrollmentJpaRepository) {
        this.enrollmentJpaRepository = enrollmentJpaRepository;
    }

    @Override
    public Enrollment create(Enrollment enrollment) {
        if (enrollment == null) {
            return null;
        }
        return enrollmentJpaRepository.save(enrollment);
    }

    @Override
    public List<Enrollment> findAll() {
        return enrollmentJpaRepository.findAll();
    }

    @Override
    public Enrollment findById(Long enrollmentId) {
        return enrollmentJpaRepository.findById(enrollmentId).orElse(null);
    }

    @Override
    public boolean delete(Long enrollmentId) {
        if (!enrollmentJpaRepository.existsById(enrollmentId)) {
            return false;
        }
        enrollmentJpaRepository.deleteById(enrollmentId);
        return true;
    }

    @Override
    public boolean update(Enrollment enrollment) {
        if (enrollment == null || enrollment.getEnrollmentId() == null
                || !enrollmentJpaRepository.existsById(enrollment.getEnrollmentId())) {
            return false;
        }
        enrollmentJpaRepository.save(enrollment);
        return true;
    }

    @Override
    public boolean existsById(Long enrollmentId) {
        if (enrollmentId == null || enrollmentId <= 0) {
            return false;
        }

        return enrollmentJpaRepository.existsById(enrollmentId);
    }

    @Override
    public int count() {
        return (int) enrollmentJpaRepository.count();
    }


}

