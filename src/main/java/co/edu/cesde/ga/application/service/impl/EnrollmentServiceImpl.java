package co.edu.cesde.ga.application.service.impl;

import co.edu.cesde.ga.exceptions.DuplicateException;
import co.edu.cesde.ga.exceptions.InvalidDataException;
import co.edu.cesde.ga.exceptions.NotFoundException;
import co.edu.cesde.ga.domain.model.Enrollment;
import co.edu.cesde.ga.application.port.out.EnrollmentRepository;
import co.edu.cesde.ga.application.port.in.EnrollmentService;
import java.util.List;

public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;

    public EnrollmentServiceImpl(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    @Override
    public Enrollment create(Enrollment enrollment) {

        if (enrollment == null) {
            throw new InvalidDataException("La inscripción no puede ser nula");
        }

        if (isInvalidEnrollment(enrollment)) {
            throw new InvalidDataException("Datos de inscripción inválidos");
        }

        if (enrollmentRepository.existsById(enrollment.getEnrollmentId())) {
            throw new DuplicateException("La inscripción ya existe");
        }

        return enrollmentRepository.create(enrollment);
    }

    @Override
    public boolean update(Enrollment enrollment) {

        if (enrollment == null || enrollment.getEnrollmentId() == null || enrollment.getEnrollmentId() <= 0L) {
            throw new InvalidDataException("ID inválido");
        }

        if (!enrollmentRepository.existsById(enrollment.getEnrollmentId())) {
            throw new NotFoundException("Inscripción no encontrada");
        }

        if (isInvalidEnrollment(enrollment)) {
            throw new InvalidDataException("Datos inválidos");
        }

        return enrollmentRepository.update(enrollment);
    }

    @Override
    public boolean delete(Long enrollmentId) {

        if (enrollmentId == null || enrollmentId <= 0L) {
            throw new InvalidDataException("ID inválido");
        }

        if (!enrollmentRepository.existsById(enrollmentId)) {
            throw new NotFoundException("Inscripción no encontrada");
        }

        return enrollmentRepository.delete(enrollmentId);
    }

    @Override
    public Enrollment findById(Long enrollmentId) {

        if (enrollmentId == null || enrollmentId <= 0L) {
            throw new InvalidDataException("ID inválido");
        }

        Enrollment enrollment = enrollmentRepository.findById(enrollmentId);

        if (enrollment == null) {
            throw new NotFoundException("Inscripción no encontrada");
        }

        return enrollment;
    }
    @Override
    public List<Enrollment> findAll() {
        return enrollmentRepository.findAll();
    }

    @Override
    public boolean existsById(Long enrollmentId) {
        return enrollmentRepository.existsById(enrollmentId);
    }

    private boolean isInvalidEnrollment(Enrollment enrollment) {
        return enrollment == null
                || enrollment.getEnrollmentId() == null
                || enrollment.getStudent().getStudentId() == null
                || enrollment.getGroup().getGroupId() == null
                || enrollment.getPeriod().getPeriodId() == null
                || !isNotBlank(enrollment.getStatus());
    }

    private boolean isNotBlank(String value) {
        return value != null && !value.trim().isEmpty();
    }
}
