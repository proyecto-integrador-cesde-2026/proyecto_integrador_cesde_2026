package co.edu.cesde.ga.infrastructure.adapter.in.rest;

import co.edu.cesde.ga.domain.model.Period;
import co.edu.cesde.ga.domain.model.port.in.PeriodService;
import co.edu.cesde.ga.infrastructure.adapter.in.rest.dto.request.PeriodRequestDTO;
import co.edu.cesde.ga.infrastructure.adapter.in.rest.dto.response.PeriodResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/periods")
public class PeriodController {

    private final PeriodService periodService;

    public PeriodController(PeriodService periodService) {
        this.periodService = periodService;
    }

    @GetMapping
    public ResponseEntity<List<Period>> findAll() {
        return ResponseEntity.ok(periodService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Period> findById(@PathVariable Long id) {
        return ResponseEntity.ok(periodService.findById(id));
    }

    @PostMapping
    public ResponseEntity<PeriodResponseDTO> create(
            @Valid @RequestBody PeriodRequestDTO dto) {

        Period period = new Period();
        period.setName(dto.getName());
        period.setStartDate(dto.getStartDate());
        period.setEndDate(dto.getEndDate());

        Period createdPeriod = periodService.create(period);

        PeriodResponseDTO response = new PeriodResponseDTO();
        response.setPeriodId(createdPeriod.getPeriodId());
        response.setName(createdPeriod.getName());
        response.setStartDate(createdPeriod.getStartDate());
        response.setEndDate(createdPeriod.getEndDate());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }



    @PutMapping("/{id}")
    public ResponseEntity<PeriodResponseDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody PeriodRequestDTO dto) {

        Period period = new Period();
        period.setPeriodId(id);
        period.setName(dto.getName());
        period.setStartDate(dto.getStartDate());
        period.setEndDate(dto.getEndDate());

        boolean updated = periodService.update(period);

        if (!updated) {
            return ResponseEntity.notFound().build();
        }

        PeriodResponseDTO response = new PeriodResponseDTO();
        response.setPeriodId(period.getPeriodId());
        response.setName(period.getName());
        response.setStartDate(period.getStartDate());
        response.setEndDate(period.getEndDate());

        return ResponseEntity.ok(response);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean deleted = periodService.delete(id);

        if (deleted) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}

