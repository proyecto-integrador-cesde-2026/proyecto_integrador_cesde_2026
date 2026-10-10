package co.edu.cesde.ga.infrastructure.adapter.in.rest;

import co.edu.cesde.ga.domain.model.Teacher;
import co.edu.cesde.ga.domain.model.port.in.TeacherService;
import co.edu.cesde.ga.infrastructure.adapter.in.rest.dto.request.TeacherRequestDTO;
import co.edu.cesde.ga.infrastructure.adapter.in.rest.dto.response.TeacherResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping
    public ResponseEntity<List<Teacher>> findAll() {
        return ResponseEntity.ok(teacherService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Teacher> findById(@PathVariable Long id) {
        return ResponseEntity.ok(teacherService.findById(id));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Teacher> findByName(@PathVariable String name) {
        return ResponseEntity.ok(teacherService.findByName(name));
    }

    @PostMapping
    public ResponseEntity<TeacherResponseDTO> create(
            @Valid @RequestBody TeacherRequestDTO dto) {

        Teacher teacher = new Teacher();

        teacher.setTeacherId(dto.getTeacherId());
        teacher.setUserId(dto.getUserId());
        teacher.setCode(dto.getCode());
        teacher.setDocumentNumber(dto.getDocumentNumber());
        teacher.setFirstName(dto.getFirstName());
        teacher.setLastName(dto.getLastName());
        teacher.setStatus(dto.getStatus());

        Teacher createdTeacher = teacherService.create(teacher);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(toResponseDTO(createdTeacher));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeacherResponseDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody TeacherRequestDTO dto) {

        Teacher teacher = new Teacher();

        teacher.setTeacherId(id);
        teacher.setUserId(dto.getUserId());
        teacher.setCode(dto.getCode());
        teacher.setDocumentNumber(dto.getDocumentNumber());
        teacher.setFirstName(dto.getFirstName());
        teacher.setLastName(dto.getLastName());
        teacher.setStatus(dto.getStatus());

        Teacher updatedTeacher = teacherService.update(teacher);

        return ResponseEntity.ok(toResponseDTO(updatedTeacher));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        teacherService.delete(id);

        return ResponseEntity.noContent().build();
    }



    private TeacherResponseDTO toResponseDTO(Teacher teacher) {
        TeacherResponseDTO dto = new TeacherResponseDTO();

        dto.setTeacherId(teacher.getTeacherId());
        dto.setUserId(teacher.getUserId());
        dto.setCode(teacher.getCode());
        dto.setDocumentNumber(teacher.getDocumentNumber());
        dto.setFirstName(teacher.getFirstName());
        dto.setLastName(teacher.getLastName());
        dto.setStatus(teacher.getStatus());

        return dto;
    }
}
