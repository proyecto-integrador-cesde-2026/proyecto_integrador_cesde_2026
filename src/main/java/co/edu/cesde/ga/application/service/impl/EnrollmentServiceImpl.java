package co.edu.cesde.ga.application.service.impl;

import co.edu.cesde.ga.domain.model.exceptions.DuplicateException;
import co.edu.cesde.ga.domain.model.exceptions.InvalidDataException;
import co.edu.cesde.ga.domain.model.exceptions.NotFoundException;
import co.edu.cesde.ga.domain.model.Enrollment;
import co.edu.cesde.ga.domain.model.port.out.EnrollmentRepository;
import co.edu.cesde.ga.domain.model.port.in.EnrollmentService;
import co.edu.cesde.ga.domain.model.port.out.GroupRepository;
import co.edu.cesde.ga.domain.model.port.out.PeriodRepository;
import co.edu.cesde.ga.domain.model.port.out.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;
    private final PeriodRepository periodRepository;

    public EnrollmentServiceImpl(EnrollmentRepository enrollmentRepository ,
                                 StudentRepository studentRepository,
                                 GroupRepository groupRepository,
                                 PeriodRepository periodRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.studentRepository = studentRepository;
        this.groupRepository = groupRepository;
        this.periodRepository = periodRepository;
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
                || enrollment.getStudent() == null
                || enrollment.getStudent().getStudentId() == null
                || enrollment.getGroup() == null
                || enrollment.getGroup().getGroupId() == null
                || enrollment.getPeriod() == null
                || enrollment.getPeriod().getPeriodId() == null
                || !isNotBlank(enrollment.getStatus());
    }

    public Enrollment createFromIds(
            Long studentId,
            Long groupId,
            Long periodId,
            String status) {

        if (studentId == null || studentId <= 0
                || groupId == null || groupId <= 0
                || periodId == null || periodId <= 0
                || status == null || status.trim().isEmpty()) {
            throw new InvalidDataException("Datos de matrícula inválidos");
        }

        Enrollment enrollment = new Enrollment();

        enrollment.setStudent(studentRepository.findById(studentId));
        enrollment.setGroup(groupRepository.findById(groupId));
        enrollment.setPeriod(periodRepository.findById(periodId));
        enrollment.setStatus(status);

        return create(enrollment);
    }


    private boolean isNotBlank(String value) {
        return value != null && !value.trim().isEmpty();
    }
}
