package co.edu.cesde.ga.infrastructure.adapter.in.rest;

import co.edu.cesde.ga.domain.model.Programs;
import co.edu.cesde.ga.domain.model.port.in.ProgramService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import co.edu.cesde.ga.infrastructure.adapter.in.rest.dto.request.ProgramsRequestDTO;
import co.edu.cesde.ga.infrastructure.adapter.in.rest.dto.response.ProgramsResponseDTO;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/programs")
public class ProgramsController {

    private final ProgramService programService;

    public ProgramsController(ProgramService programService) {
        this.programService = programService;
    }

    @GetMapping
    public ResponseEntity<List<Programs>> findAll() {
        return ResponseEntity.ok(programService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Programs> findById(@PathVariable Long id) {
        return ResponseEntity.ok(programService.findById(id));
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<Programs> findByCode(@PathVariable String code) {
        return ResponseEntity.ok(programService.findByCode(code));
    }

    @PostMapping
    public ResponseEntity<ProgramsResponseDTO> create(
            @Valid @RequestBody ProgramsRequestDTO dto) {

        Programs program = new Programs();
        program.setCode(dto.getCode());
        program.setName(dto.getName());

        Programs createdProgram = programService.create(program);

        ProgramsResponseDTO response = new ProgramsResponseDTO();
        response.setProgramId(createdProgram.getProgramId());
        response.setCode(createdProgram.getCode());
        response.setName(createdProgram.getName());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProgramsResponseDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody ProgramsRequestDTO dto) {

        Programs program = new Programs();
        program.setProgramId(id);
        program.setCode(dto.getCode());
        program.setName(dto.getName());

        boolean updated = programService.update(program);

        if (!updated) {
            return ResponseEntity.notFound().build();
        }

        ProgramsResponseDTO response = new ProgramsResponseDTO();
        response.setProgramId(program.getProgramId());
        response.setCode(program.getCode());
        response.setName(program.getName());

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean deleted = programService.delete(id);

        if (deleted) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}

