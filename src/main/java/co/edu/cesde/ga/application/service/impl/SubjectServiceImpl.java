package co.edu.cesde.ga.application.service.impl;

import co.edu.cesde.ga.domain.model.exceptions.DuplicateException;
import co.edu.cesde.ga.domain.model.exceptions.InvalidDataException;
import co.edu.cesde.ga.domain.model.exceptions.NotFoundException;
import co.edu.cesde.ga.domain.model.Subject;
import co.edu.cesde.ga.domain.model.port.out.SubjectsRepository;
import co.edu.cesde.ga.domain.model.port.in.SubjectService;
import co.edu.cesde.ga.domain.model.Programs;
import co.edu.cesde.ga.domain.model.port.out.ProgramsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SubjectServiceImpl implements SubjectService {

    private final SubjectsRepository subjectsRepository;
    private final ProgramsRepository programsRepository;

    public SubjectServiceImpl(
            SubjectsRepository subjectsRepository,
            ProgramsRepository programsRepository) {
        this.subjectsRepository = subjectsRepository;
        this.programsRepository = programsRepository;
    }

    @Override
    public Subject create(Subject subject) {

        if (subject == null) {
            throw new InvalidDataException("La materia no puede ser nula");
        }

        if (subject.getProgram() == null
                || subject.getProgram().getProgramId() == null) {
            throw new InvalidDataException("El programa es obligatorio");
        }

        Programs program = programsRepository.findById(
                subject.getProgram().getProgramId()
        );

        if (program == null) {
            throw new NotFoundException("Programa no encontrado");
        }

        subject.setProgram(program);

        if (isInvalidSubject(subject)) {
            throw new InvalidDataException("Datos inválidos");
        }

        if (subjectsRepository.existsByCode(subject.getCode())) {
            throw new DuplicateException(
                    "Ya existe una materia con ese código"
            );
        }

        return subjectsRepository.create(subject);
    }


    @Override
    public boolean update(Subject subject) {

        if (subject == null
                || subject.getSubjectId() == null
                || subject.getSubjectId() <= 0L) {
            throw new InvalidDataException("ID inválido");
        }

        if (!subjectsRepository.existsById(subject.getSubjectId())) {
            throw new NotFoundException("Materia no encontrada");
        }

        if (subject.getProgram() == null
                || subject.getProgram().getProgramId() == null) {
            throw new InvalidDataException("El programa es obligatorio");
        }

        Programs program = programsRepository.findById(
                subject.getProgram().getProgramId()
        );

        if (program == null) {
            throw new NotFoundException("Programa no encontrado");
        }

        subject.setProgram(program);

        if (isInvalidSubject(subject)) {
            throw new InvalidDataException("Datos inválidos");
        }

        Subject existingSubject = subjectsRepository.findByCode(
                subject.getCode()
        );

        if (existingSubject != null
                && !existingSubject.getSubjectId().equals(subject.getSubjectId())) {
            throw new DuplicateException(
                    "Ya existe otra materia con ese código"
            );
        }

        return subjectsRepository.update(subject);
    }

    @Override
    public boolean delete(String code) {

        if (!isNotBlank(code)) {
            throw new InvalidDataException("Código inválido");
        }

        if (!subjectsRepository.existsByCode(code)) {
            throw new NotFoundException("Materia no encontrada");
        }

        return subjectsRepository.delete(code);
    }


    @Override
    public Subject findByCode(String code) {

        if (!isNotBlank(code)) {
            throw new InvalidDataException("Código inválido");
        }

        Subject subject = subjectsRepository.findByCode(code);

        if (subject == null) {
            throw new NotFoundException("Materia no encontrada");
        }

        return subject;
    }


    @Override
    public boolean existsByCode(String code) {

        if (!isNotBlank(code)) {
            throw new InvalidDataException("Código inválido");
        }

        return subjectsRepository.existsByCode(code);
    }

    @Override
    public List<Subject> findAll() {
        return subjectsRepository.findAll();
    }


    private boolean isInvalidSubject(Subject subject) {

        return subject == null
                || subject.getSubjectId() <= 0L
                || !isNotBlank(subject.getCode())
                || !isNotBlank(subject.getName())
                || subject.getCredits() <= 0
                || subject.getProgram() == null
                || subject.getProgram().getProgramId() == null
                || subject.getProgram().getProgramId() <= 0L;
    }

    private boolean isNotBlank(String value) {
        return value != null && !value.trim().isEmpty();
    }
}