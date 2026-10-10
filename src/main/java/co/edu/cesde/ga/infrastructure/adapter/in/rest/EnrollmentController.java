package co.edu.cesde.ga.infrastructure.adapter.in.rest;

import co.edu.cesde.ga.domain.model.port.in.EnrollmentService;
import co.edu.cesde.ga.domain.model.Enrollment;
import co.edu.cesde.ga.infrastructure.adapter.in.rest.dto.request.EnrollmentRequestDTO;
import jakarta.validation.Valid;
import co.edu.cesde.ga.infrastructure.adapter.in.rest.dto.response.EnrollmentResponseDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @PostMapping
    public EnrollmentResponseDTO create(@Valid @RequestBody EnrollmentRequestDTO dto) {
        Enrollment enrollment = enrollmentService.createFromIds(
                dto.getStudentId(),
                dto.getGroupId(),
                dto.getPeriodId(),
                dto.getStatus()
        );
        EnrollmentResponseDTO response = new EnrollmentResponseDTO();
        response.setEnrollmentId(enrollment.getEnrollmentId());
        response.setStudentId(enrollment.getStudent().getStudentId());
        response.setGroupId(enrollment.getGroup().getGroupId());
        response.setPeriodId(enrollment.getPeriod().getPeriodId());
        response.setStatus(enrollment.getStatus());

        return response;
    }

    @GetMapping
    public List<Enrollment> findAll() {
        return enrollmentService.findAll();
    }

    @GetMapping("/{id}")
    public Enrollment findById(@PathVariable Long id) {
        return enrollmentService.findById(id);
    }

    @PutMapping("/{id}")
    public EnrollmentResponseDTO update(@PathVariable Long id, @Valid @RequestBody EnrollmentRequestDTO dto) {
        Enrollment enrollment = enrollmentService.findById(id);
        enrollment.setStatus(dto.getStatus());
        enrollmentService.update(enrollment);
        EnrollmentResponseDTO response = new EnrollmentResponseDTO();
        response.setEnrollmentId(enrollment.getEnrollmentId());
        response.setStudentId(enrollment.getStudent().getStudentId());
        response.setGroupId(enrollment.getGroup().getGroupId());
        response.setPeriodId(enrollment.getPeriod().getPeriodId());
        response.setStatus(enrollment.getStatus());
        return response;
    }
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id) {
        return enrollmentService.delete(id);
    }
}

