package co.edu.cesde.ga.infrastructure.adapter.in.rest;

import co.edu.cesde.ga.domain.model.GroupSubjects;
import co.edu.cesde.ga.domain.model.Groups;
import co.edu.cesde.ga.domain.model.Subject;
import co.edu.cesde.ga.domain.model.Teacher;
import co.edu.cesde.ga.domain.model.port.in.GroupSubjectService;
import co.edu.cesde.ga.infrastructure.adapter.in.rest.dto.request.GroupSubjectsRequestDTO;
import co.edu.cesde.ga.infrastructure.adapter.in.rest.dto.response.GroupSubjectsResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/group-subjects")
public class GroupSubjectsController {

    private final GroupSubjectService groupSubjectService;

    public GroupSubjectsController(GroupSubjectService groupSubjectService) {
        this.groupSubjectService = groupSubjectService;
    }

    @GetMapping
    public ResponseEntity<List<GroupSubjects>> findAll() {
        return ResponseEntity.ok(groupSubjectService.findAll());
    }

    @GetMapping("/{groupId}/{subjectId}")
    public ResponseEntity<GroupSubjects> findByIds(
            @PathVariable Long groupId,
            @PathVariable Long subjectId) {

        return ResponseEntity.ok(
                groupSubjectService.findByIds(groupId, subjectId)
        );
    }

    @PostMapping
    public ResponseEntity<GroupSubjectsResponseDTO> create(
            @Valid @RequestBody GroupSubjectsRequestDTO dto) {

        GroupSubjects groupSubject = new GroupSubjects();

        Groups group = new Groups();
        group.setGroupId(dto.getGroupId());
        groupSubject.setGroup(group);

        Subject subject = new Subject();
        subject.setSubjectId(dto.getSubjectId());
        groupSubject.setSubject(subject);

        Teacher teacher = new Teacher();
        teacher.setTeacherId(dto.getTeacherId());
        groupSubject.setTeacher(teacher);

        GroupSubjects created = groupSubjectService.create(groupSubject);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(toResponseDTO(created));
    }

    @PutMapping
    public ResponseEntity<?> update(
            @Valid @RequestBody GroupSubjectsRequestDTO dto) {

        GroupSubjects groupSubject = new GroupSubjects();

        Groups group = new Groups();
        group.setGroupId(dto.getGroupId());
        groupSubject.setGroup(group);

        Subject subject = new Subject();
        subject.setSubjectId(dto.getSubjectId());
        groupSubject.setSubject(subject);

        Teacher teacher = new Teacher();
        teacher.setTeacherId(dto.getTeacherId());
        groupSubject.setTeacher(teacher);

        boolean updated = groupSubjectService.update(groupSubject);

        if (!updated) {
            return ResponseEntity.notFound().build();
        }

        GroupSubjects updatedGroupSubject =
                groupSubjectService.findByIds(
                        dto.getGroupId(),
                        dto.getSubjectId()
                );

        return ResponseEntity.ok(toResponseDTO(updatedGroupSubject));
    }

    @DeleteMapping("/{groupId}/{subjectId}")
    public ResponseEntity<Void> delete(
            @PathVariable Long groupId,
            @PathVariable Long subjectId) {

        boolean deleted = groupSubjectService.delete(groupId, subjectId);

        if (deleted) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

    private GroupSubjectsResponseDTO toResponseDTO(
            GroupSubjects groupSubject) {

        GroupSubjectsResponseDTO dto = new GroupSubjectsResponseDTO();

        dto.setGroupSubjectId(groupSubject.getGroupSubjectId());
        dto.setGroupId(groupSubject.getGroup().getGroupId());
        dto.setSubjectId(groupSubject.getSubject().getSubjectId());
        dto.setTeacherId(groupSubject.getTeacher().getTeacherId());

        return dto;
    }
}

