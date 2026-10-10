package co.edu.cesde.ga.infrastructure.adapter.in.rest;

import co.edu.cesde.ga.domain.model.port.in.StudentService;
import co.edu.cesde.ga.domain.model.Student;
import co.edu.cesde.ga.infrastructure.adapter.in.rest.dto.request.StudentRequestDTO;
import co.edu.cesde.ga.infrastructure.adapter.in.rest.dto.response.StudentResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<Student>> findAll() {
        return ResponseEntity.ok(studentService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> findById(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.findById(id));
    }

    @PostMapping
    public ResponseEntity<StudentResponseDTO> create(
            @Valid @RequestBody StudentRequestDTO studentDTO) {

        Student student = new Student(
                null,
                studentDTO.getCode(),
                studentDTO.getDocumentNumber(),
                studentDTO.getFirstName(),
                studentDTO.getLastName(),
                studentDTO.getStatus(),
                studentDTO.getBirthDate()
        );

        Student createdStudent = studentService.create(student);

        StudentResponseDTO response = new StudentResponseDTO();
        response.setStudentId(createdStudent.getStudentId());
        response.setCode(createdStudent.getCode());
        response.setDocumentNumber(createdStudent.getDocumentNumber());
        response.setFirstName(createdStudent.getFirstName());
        response.setLastName(createdStudent.getLastName());
        response.setStatus(createdStudent.getStatus());
        response.setBirthDate(createdStudent.getBirthDate());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }






    @PutMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody StudentRequestDTO studentDTO) {

        Student student = new Student(
                id,
                studentDTO.getCode(),
                studentDTO.getDocumentNumber(),
                studentDTO.getFirstName(),
                studentDTO.getLastName(),
                studentDTO.getStatus(),
                studentDTO.getBirthDate()
        );

        studentService.update(student);

        Student updatedStudent = studentService.findById(id);

        StudentResponseDTO response = new StudentResponseDTO();
        response.setStudentId(updatedStudent.getStudentId());
        response.setCode(updatedStudent.getCode());
        response.setDocumentNumber(updatedStudent.getDocumentNumber());
        response.setFirstName(updatedStudent.getFirstName());
        response.setLastName(updatedStudent.getLastName());
        response.setStatus(updatedStudent.getStatus());
        response.setBirthDate(updatedStudent.getBirthDate());

        return ResponseEntity.ok(response);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        studentService.delete(id);

        return ResponseEntity.noContent().build();
    }
}