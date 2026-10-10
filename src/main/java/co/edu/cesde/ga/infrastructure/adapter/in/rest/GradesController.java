
package co.edu.cesde.ga.infrastructure.adapter.in.rest;

import co.edu.cesde.ga.domain.model.Grade;
import co.edu.cesde.ga.domain.model.GroupSubjects;
import co.edu.cesde.ga.domain.model.Student;
import co.edu.cesde.ga.domain.model.port.in.GradesService;
import co.edu.cesde.ga.infrastructure.adapter.in.rest.dto.request.GradeRequestDTO;
import co.edu.cesde.ga.infrastructure.adapter.in.rest.dto.response.GradeResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grades")
public class GradesController {

    private final GradesService gradesService;

    public GradesController(GradesService gradesService) {
        this.gradesService = gradesService;
    }

    @GetMapping
    public ResponseEntity<List<Grade>> findAll() {
        return ResponseEntity.ok(gradesService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Grade> findById(@PathVariable Long id) {
        return ResponseEntity.ok(gradesService.findById(id));
    }

    @PostMapping
    public ResponseEntity<GradeResponseDTO> create(
            @Valid @RequestBody GradeRequestDTO dto) {

        Grade grade = new Grade();

        GroupSubjects groupSubject = new GroupSubjects();
        groupSubject.setGroupSubjectId(dto.getGroupSubjectId());
        grade.setGroupSubject(groupSubject);

        Student student = new Student();
        student.setStudentId(dto.getStudentId());
        grade.setStudent(student);

        grade.setFinalScore(dto.getFinalScore());
        grade.setObservation(dto.getObservation());

        Grade createdGrade = gradesService.create(grade);

        GradeResponseDTO response = new GradeResponseDTO();
        response.setGradeId(createdGrade.getGradeId());
        response.setGroupSubjectId(
                createdGrade.getGroupSubject().getGroupSubjectId());
        response.setStudentId(
                createdGrade.getStudent().getStudentId());
        response.setFinalScore(createdGrade.getFinalScore());
        response.setObservation(createdGrade.getObservation());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable Long id,
            @Valid @RequestBody GradeRequestDTO dto) {

        Grade grade = new Grade();
        grade.setGradeId(id);

        GroupSubjects groupSubject = new GroupSubjects();
        groupSubject.setGroupSubjectId(dto.getGroupSubjectId());
        grade.setGroupSubject(groupSubject);

        Student student = new Student();
        student.setStudentId(dto.getStudentId());
        grade.setStudent(student);

        grade.setFinalScore(dto.getFinalScore());
        grade.setObservation(dto.getObservation());

        boolean updated = gradesService.update(grade);

        if (!updated) {
            return ResponseEntity.notFound().build();
        }

        Grade updatedGrade = gradesService.findById(id);

        GradeResponseDTO response = new GradeResponseDTO();
        response.setGradeId(updatedGrade.getGradeId());
        response.setGroupSubjectId(
                updatedGrade.getGroupSubject().getGroupSubjectId());
        response.setStudentId(
                updatedGrade.getStudent().getStudentId());
        response.setFinalScore(updatedGrade.getFinalScore());
        response.setObservation(updatedGrade.getObservation());

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean deleted = gradesService.delete(id);

        if (deleted) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}