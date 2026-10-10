package co.edu.cesde.ga.application.service.impl;

import co.edu.cesde.ga.domain.model.exceptions.DuplicateException;
import co.edu.cesde.ga.domain.model.exceptions.InvalidDataException;
import co.edu.cesde.ga.domain.model.exceptions.NotFoundException;
import co.edu.cesde.ga.domain.model.Programs;
import co.edu.cesde.ga.domain.model.port.out.ProgramsRepository;
import co.edu.cesde.ga.domain.model.port.in.ProgramService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProgramServiceImpl implements ProgramService {

    private final ProgramsRepository programsRepository;

    public ProgramServiceImpl(ProgramsRepository programsRepository) {
        this.programsRepository = programsRepository;
    }

    @Override
    public Programs create(Programs program) {

        if (program == null) {
            throw new InvalidDataException("El programa no puede ser nulo");
        }

        if (isInvalidProgram(program)) {
            throw new InvalidDataException("Datos inválidos");
        }

        if (programsRepository.existsByCode(program.getCode())) {
            throw new DuplicateException("Ya existe un programa con ese código");
        }

        return programsRepository.create(program);
    }

    @Override
    public boolean update(Programs program) {

        if (program == null || program.getProgramId() <= 0L) {
            throw new InvalidDataException("ID inválido");
        }

        if (programsRepository.findById(program.getProgramId()) == null) {
            throw new NotFoundException("Programa no encontrado");
        }

        if (isInvalidProgram(program)) {
            throw new InvalidDataException("Datos inválidos");
        }

        return programsRepository.update(program);
    }

    @Override
    public boolean delete(Long programId) {

        if (programId == null || programId <= 0L) {
            throw new InvalidDataException("ID inválido");
        }

        if (programsRepository.findById(programId) == null) {
            throw new NotFoundException("Programa no encontrado");
        }

        return programsRepository.delete(programId);
    }


    @Override
    public Programs findById(Long programId) {

        if (programId == null || programId <= 0L) {
            throw new InvalidDataException("ID inválido");
        }

        Programs program = programsRepository.findById(programId);

        if (program == null) {
            throw new NotFoundException("Programa no encontrado");
        }

        return program;
    }

    @Override
    public Programs findByCode(String code) {

        if (!isNotBlank(code)) {
            throw new InvalidDataException("Código inválido");
        }

        Programs program = programsRepository.findByCode(code);

        if (program == null) {
            throw new NotFoundException("Programa no encontrado");
        }

        return program;
    }


    @Override
    public boolean existsByCode(String code) {

        if (!isNotBlank(code)) {
            throw new InvalidDataException("Código inválido");
        }

        return programsRepository.existsByCode(code);
    }

    @Override
    public List<Programs> findAll() {
        return programsRepository.findAll();
    }

    private boolean isInvalidProgram(Programs program) {

        return program == null
                || program.getProgramId() <= 0L
                || !isNotBlank(program.getCode())
                || !isNotBlank(program.getName());
    }

    private boolean isNotBlank(String value) {
        return value != null && !value.trim().isEmpty();
    }
}