package co.edu.cesde.ga.infrastructure.adapter.in.rest;

import co.edu.cesde.ga.domain.model.Groups;
import co.edu.cesde.ga.domain.model.Period;
import co.edu.cesde.ga.domain.model.Programs;
import co.edu.cesde.ga.domain.model.port.in.GroupsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import co.edu.cesde.ga.infrastructure.adapter.in.rest.dto.request.GroupsRequestDTO;
import co.edu.cesde.ga.infrastructure.adapter.in.rest.dto.response.GroupsResponseDTO;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/groups")
public class GroupsController {

    private final GroupsService groupsService;

    public GroupsController(GroupsService groupsService) {
        this.groupsService = groupsService;
    }

    @GetMapping
    public ResponseEntity<List<Groups>> findAll() {
        return ResponseEntity.ok(groupsService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Groups> findById(@PathVariable Long id) {
        return ResponseEntity.ok(groupsService.findById(id));
    }



    @PostMapping
    public ResponseEntity<GroupsResponseDTO> create(
            @Valid @RequestBody GroupsRequestDTO dto) {

        Groups group = new Groups();

        group.setCode(dto.getCode());
        group.setShift(dto.getShift());

        co.edu.cesde.ga.domain.model.Programs program =
                new co.edu.cesde.ga.domain.model.Programs();
        program.setProgramId(dto.getProgramId());
        group.setProgram(program);

        co.edu.cesde.ga.domain.model.Period period =
                new co.edu.cesde.ga.domain.model.Period();
        period.setPeriodId(dto.getPeriodId());
        group.setPeriod(period);

        Groups createdGroup = groupsService.create(group);

        GroupsResponseDTO response = new GroupsResponseDTO();
        response.setGroupId(createdGroup.getGroupId());
        response.setCode(createdGroup.getCode());
        response.setProgramId(createdGroup.getProgram().getProgramId());
        response.setPeriodId(createdGroup.getPeriod().getPeriodId());
        response.setShift(createdGroup.getShift());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @PutMapping("/{id}")
    public ResponseEntity<GroupsResponseDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody GroupsRequestDTO dto) {

        Groups group = new Groups();
        group.setGroupId(id);
        group.setCode(dto.getCode());
        group.setShift(dto.getShift());

        Programs program = new Programs();
        program.setProgramId(dto.getProgramId());
        group.setProgram(program);

        Period period = new Period();
        period.setPeriodId(dto.getPeriodId());
        group.setPeriod(period);

        boolean updated = groupsService.update(group);

        if (!updated) {
            return ResponseEntity.notFound().build();
        }

        GroupsResponseDTO response = new GroupsResponseDTO();
        response.setGroupId(group.getGroupId());
        response.setCode(group.getCode());
        response.setProgramId(group.getProgram().getProgramId());
        response.setPeriodId(group.getPeriod().getPeriodId());
        response.setShift(group.getShift());

        return ResponseEntity.ok(response);
    }




    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean deleted = groupsService.delete(id);

        if (deleted) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}

