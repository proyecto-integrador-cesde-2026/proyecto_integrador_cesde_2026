package co.edu.cesde.ga.application.service.impl;

import co.edu.cesde.ga.domain.model.exceptions.DuplicateException;
import co.edu.cesde.ga.domain.model.exceptions.InvalidDataException;
import co.edu.cesde.ga.domain.model.exceptions.NotFoundException;
import co.edu.cesde.ga.domain.model.GroupSubjects;
import co.edu.cesde.ga.domain.model.port.out.GroupSubjectsRepository;
import co.edu.cesde.ga.domain.model.port.in.GroupSubjectService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GroupSubjectServiceImpl implements GroupSubjectService {

    private final GroupSubjectsRepository groupSubjectsRepository;

    public GroupSubjectServiceImpl(GroupSubjectsRepository groupSubjectsRepository) {
        this.groupSubjectsRepository = groupSubjectsRepository;
    }

    @Override
    public GroupSubjects create(GroupSubjects groupSubject) {

        if (groupSubject == null) {
            throw new InvalidDataException("La asignación no puede ser nula");
        }

        if (isInvalidGroupSubject(groupSubject)) {
            throw new InvalidDataException("Datos inválidos");
        }

        if (groupSubjectsRepository.exists(
                groupSubject.getGroup().getGroupId(),
                groupSubject.getSubject().getSubjectId())) {

            throw new DuplicateException("La materia ya está asignada al grupo");
        }

        return groupSubjectsRepository.create(groupSubject);
    }

    @Override
    public boolean update(GroupSubjects groupSubject) {

        if (groupSubject == null) {
            throw new InvalidDataException("Datos inválidos");
        }

        if (isInvalidGroupSubject(groupSubject)) {
            throw new InvalidDataException("Datos inválidos");
        }

        if (!groupSubjectsRepository.exists(
                groupSubject.getGroup().getGroupId(),
                groupSubject.getSubject().getSubjectId())) {

            throw new NotFoundException("Asignación no encontrada");
        }

        return groupSubjectsRepository.update(groupSubject);
    }

    @Override
    public boolean delete(Long groupId, Long subjectId) {

        if (groupId == null || groupId <= 0L ||
                subjectId == null || subjectId <= 0L) {

            throw new InvalidDataException("Datos inválidos");
        }

        if (!groupSubjectsRepository.exists(groupId, subjectId)) {
            throw new NotFoundException("Asignación no encontrada");
        }

        return groupSubjectsRepository.delete(groupId, subjectId);
    }

    @Override
    public GroupSubjects findByIds(Long groupId, Long subjectId) {

        if (groupId == null || groupId <= 0L ||
                subjectId == null || subjectId <= 0L) {

            throw new InvalidDataException("Datos inválidos");
        }

        GroupSubjects groupSubjects =
                groupSubjectsRepository.findByIds(groupId, subjectId);

        if (groupSubjects == null) {
            throw new NotFoundException("Asignación no encontrada");
        }

        return groupSubjects;
    }

    @Override
    public boolean exists(Long groupId, Long subjectId) {

        if (groupId == null || groupId <= 0L ||
                subjectId == null || subjectId <= 0L) {

            throw new InvalidDataException("Datos inválidos");
        }

        return groupSubjectsRepository.exists(groupId, subjectId);
    }

    @Override
    public List<GroupSubjects> findAll() {
        return groupSubjectsRepository.findAll();
    }

    private boolean isInvalidGroupSubject(GroupSubjects groupSubject) {

        return groupSubject == null
                || groupSubject.getGroup() == null
                || groupSubject.getGroup().getGroupId() == null
                || groupSubject.getGroup().getGroupId() <= 0L
                || groupSubject.getSubject() == null
                || groupSubject.getSubject().getSubjectId() == null
                || groupSubject.getSubject().getSubjectId() <= 0L
                || groupSubject.getTeacher() == null
                || groupSubject.getTeacher().getUserId() == null
                || groupSubject.getTeacher().getUserId() <= 0L;
    }
}