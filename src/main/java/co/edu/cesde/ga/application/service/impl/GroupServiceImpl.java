package co.edu.cesde.ga.application.service.impl;

import co.edu.cesde.ga.domain.model.exceptions.DuplicateException;
import co.edu.cesde.ga.domain.model.exceptions.InvalidDataException;
import co.edu.cesde.ga.domain.model.exceptions.NotFoundException;
import co.edu.cesde.ga.domain.model.Groups;
import co.edu.cesde.ga.domain.model.port.out.GroupRepository;
import co.edu.cesde.ga.domain.model.port.in.GroupsService;
import co.edu.cesde.ga.domain.model.Programs;
import co.edu.cesde.ga.domain.model.Period;
import co.edu.cesde.ga.domain.model.port.out.ProgramsRepository;
import co.edu.cesde.ga.domain.model.port.out.PeriodRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GroupServiceImpl implements GroupsService {

    private final GroupRepository groupRepository;
    private final ProgramsRepository programsRepository;
    private final PeriodRepository periodRepository;

    public GroupServiceImpl(
            GroupRepository groupRepository,
            ProgramsRepository programsRepository,
            PeriodRepository periodRepository) {

        this.groupRepository = groupRepository;
        this.programsRepository = programsRepository;
        this.periodRepository = periodRepository;
    }


    @Override
    public Groups create(Groups groups) {

        if (groups == null) {
            throw new InvalidDataException("El grupo no puede ser nulo");
        }

        if (groups.getProgram() == null || groups.getProgram().getProgramId() == null) {
            throw new InvalidDataException("El programa es obligatorio");
        }

        if (groups.getPeriod() == null || groups.getPeriod().getPeriodId() == null) {
            throw new InvalidDataException("El período es obligatorio");
        }

        Programs program = programsRepository.findById(
                groups.getProgram().getProgramId());

        if (program == null) {
            throw new NotFoundException("Programa no encontrado");
        }

        Period period = periodRepository.findById(
                groups.getPeriod().getPeriodId());

        if (period == null) {
            throw new NotFoundException("Período no encontrado");
        }

        groups.setProgram(program);
        groups.setPeriod(period);

        if (isInvalidGroups(groups)) {
            throw new InvalidDataException("Datos del grupo inválidos");
        }

        return groupRepository.create(groups);
    }




    @Override
    public boolean update(Groups groups) {

        if (groups == null || groups.getGroupId() == null
                || groups.getGroupId() <= 0L) {
            throw new InvalidDataException("ID inválido");
        }

        if (!groupRepository.existsById(groups.getGroupId())) {
            throw new NotFoundException("Grupo no encontrado");
        }

        if (groups.getProgram() == null
                || groups.getProgram().getProgramId() == null) {
            throw new InvalidDataException("El programa es obligatorio");
        }

        if (groups.getPeriod() == null
                || groups.getPeriod().getPeriodId() == null) {
            throw new InvalidDataException("El período es obligatorio");
        }

        Programs program = programsRepository.findById(
                groups.getProgram().getProgramId());

        if (program == null) {
            throw new NotFoundException("Programa no encontrado");
        }

        Period period = periodRepository.findById(
                groups.getPeriod().getPeriodId());

        if (period == null) {
            throw new NotFoundException("Período no encontrado");
        }

        groups.setProgram(program);
        groups.setPeriod(period);

        if (isInvalidGroups(groups)) {
            throw new InvalidDataException("Datos inválidos");
        }

        return groupRepository.update(groups);
    }




    @Override
    public boolean delete(Long groupsId){

        if (groupsId == null || groupsId <= 0L) {
            throw new InvalidDataException("ID inválido");
        }

        if (!groupRepository.existsById(groupsId)) {
            throw new NotFoundException("Grupo no encontrado");
        }

        return groupRepository.delete(groupsId);
    }


    @Override
    public Groups findById(Long groupsId){

        if (groupsId == null || groupsId <= 0L) {
            throw new InvalidDataException("ID inválido");
        }

        Groups groups = groupRepository.findById(groupsId);

        if (groups == null) {
            throw new NotFoundException("Grupo no encontrado");
        }

        return groups;
    }


    @Override
    public List<Groups> findAll(){
        return groupRepository.findAll();
    }

    @Override
    public boolean existsById(Long groupsId){
        if (groupsId == null || groupsId <= 0L) {
            throw new InvalidDataException("ID inválido");
        }
        return groupRepository.existsById(groupsId);
    }

    private boolean isInvalidGroups(Groups groups){
        return groups == null
                || groups.getProgram().getProgramId() == null
                || groups.getProgram().getProgramId() <= 0L
                || groups.getPeriod().getPeriodId() == null
                || groups.getPeriod().getPeriodId() <= 0L
                || !isNotBlank(groups.getShift())
                || !isNotBlank(groups.getCode());
    }

    private boolean isNotBlank(String value){
        return value != null && !value.trim().isEmpty();
    }
}
