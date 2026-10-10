package co.edu.cesde.ga.infrastructure.adapter.in.rest;

import co.edu.cesde.ga.domain.model.Programs;
import co.edu.cesde.ga.domain.model.Subject;
import co.edu.cesde.ga.domain.model.port.in.SubjectService;
import co.edu.cesde.ga.infrastructure.adapter.in.rest.dto.request.SubjectRequestDTO;
import co.edu.cesde.ga.infrastructure.adapter.in.rest.dto.response.SubjectResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subjects")
public class SubjectController {

    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping
    public ResponseEntity<List<Subject>> findAll() {
        return ResponseEntity.ok(subjectService.findAll());
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<Subject> findByCode(@PathVariable String code) {
        return ResponseEntity.ok(subjectService.findByCode(code));
    }


    @PostMapping
    public ResponseEntity<SubjectResponseDTO> create(
            @Valid @RequestBody SubjectRequestDTO dto) {

        Subject subject = new Subject();
        subject.setCode(dto.getCode());
        subject.setName(dto.getName());
        subject.setCredits(dto.getCredits());

        Programs program = new Programs();
        program.setProgramId(dto.getProgramId());
        subject.setProgram(program);

        Subject createdSubject = subjectService.create(subject);

        SubjectResponseDTO response = new SubjectResponseDTO();
        response.setSubjectId(createdSubject.getSubjectId());
        response.setCode(createdSubject.getCode());
        response.setName(createdSubject.getName());
        response.setCredits(createdSubject.getCredits());
        response.setProgramId(
                createdSubject.getProgram().getProgramId()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }




    @PutMapping("/{subjectId}")
    public ResponseEntity<?> update(
            @PathVariable Long subjectId,
            @Valid @RequestBody SubjectRequestDTO dto) {

        Subject subject = new Subject();
        subject.setSubjectId(subjectId);
        subject.setCode(dto.getCode());
        subject.setName(dto.getName());
        subject.setCredits(dto.getCredits());

        Programs program = new Programs();
        program.setProgramId(dto.getProgramId());
        subject.setProgram(program);

        boolean updated = subjectService.update(subject);

        if (!updated) {
            return ResponseEntity.notFound().build();
        }

        Subject updatedSubject = subjectService.findByCode(dto.getCode());

        SubjectResponseDTO response = new SubjectResponseDTO();
        response.setSubjectId(updatedSubject.getSubjectId());
        response.setCode(updatedSubject.getCode());
        response.setName(updatedSubject.getName());
        response.setCredits(updatedSubject.getCredits());
        response.setProgramId(
                updatedSubject.getProgram().getProgramId()
        );

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/code/{code}")
    public ResponseEntity<Void> delete(@PathVariable String code) {
        boolean deleted = subjectService.delete(code);

        if (deleted) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}

