
package co.edu.cesde.ga.application.service.impl;

import co.edu.cesde.ga.exceptions.InvalidDataException;
import co.edu.cesde.ga.exceptions.NotFoundException;
import co.edu.cesde.ga.domain.model.Grade;
import co.edu.cesde.ga.application.port.out.GradesRepository;
import co.edu.cesde.ga.application.port.in.GradesService;
import java.util.List;

public class GradesServiceImpl implements GradesService {

    private final GradesRepository gradesRepository;

    public GradesServiceImpl(GradesRepository gradesRepository) {
        this.gradesRepository = gradesRepository;
    }

    @Override
    public Grade create(Grade grade) {

        if (grade == null) {
            throw new InvalidDataException("La nota no puede ser nula");
        }

        if (isInvalidGrade(grade)) {
            throw new InvalidDataException("Datos inválidos");
        }

        if (grade.getFinalScore() < 0 || grade.getFinalScore() > 5) {
            throw new InvalidDataException("La nota debe estar entre 0 y 5");
        }

        return gradesRepository.create(grade);
    }

    @Override
    public boolean update(Grade grade) {

        if (grade == null || grade.getGradeId() <= 0L) {
            throw new InvalidDataException("ID inválido");
        }

        if (!gradesRepository.existsById(grade.getGradeId())) {
            throw new NotFoundException("Nota no encontrada");
        }

        if (isInvalidGrade(grade)) {
            throw new InvalidDataException("Datos inválidos");
        }

        if (grade.getFinalScore() < 0 || grade.getFinalScore() > 5) {
            throw new InvalidDataException("La nota debe estar entre 0 y 5");
        }

        return gradesRepository.update(grade);
    }

    @Override
    public boolean delete(long gradeId) {

        if (gradeId <= 0L) {
            throw new InvalidDataException("ID inválido");
        }

        if (!gradesRepository.existsById(gradeId)) {
            throw new NotFoundException("Nota no encontrada");
        }

        return gradesRepository.delete(gradeId);
    }

    @Override
    public Grade findById(long gradeId) {

        if (gradeId <= 0L) {
            throw new InvalidDataException("ID inválido");
        }

        Grade grade = gradesRepository.findById(gradeId);

        if (grade == null) {
            throw new NotFoundException("Nota no encontrada");
        }

        return grade;
    }

    @Override
    public List<Grade> findAll() {
        return gradesRepository.findAll();
    }



    private boolean isInvalidGrade(Grade grade) {
        return grade == null
                || grade.getGroupSubject().getGroupSubjectId() == null
                || grade.getStudent().getStudentId() == null
                || !isNotBlank(grade.getObservation());
    }

    private boolean isNotBlank(String value) {
        return value != null && !value.trim().isEmpty();
    }
}