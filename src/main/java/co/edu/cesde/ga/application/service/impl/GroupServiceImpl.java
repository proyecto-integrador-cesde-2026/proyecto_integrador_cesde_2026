package co.edu.cesde.ga.application.service.impl;

import co.edu.cesde.ga.exceptions.DuplicateException;
import co.edu.cesde.ga.exceptions.InvalidDataException;
import co.edu.cesde.ga.exceptions.NotFoundException;
import co.edu.cesde.ga.domain.model.Groups;
import co.edu.cesde.ga.application.port.out.GroupRepository;
import co.edu.cesde.ga.application.port.in.GroupsService;

import java.util.List;

public class GroupServiceImpl implements GroupsService {

    private final GroupRepository groupRepository;

    public GroupServiceImpl(GroupRepository groupRepository){
        this.groupRepository = groupRepository;
    }

    @Override
    public Groups create(Groups groups){

        if (groups == null) {
            throw new InvalidDataException("El grupo no puede ser nulo");
        }

        if (isInvalidGroups(groups)) {
            throw new InvalidDataException("Datos del grupo inválidos");
        }

        if (groupRepository.existsById(groups.getGroupId())) {
            throw new DuplicateException("Ya existe un grupo con ese ID");
        }

        return groupRepository.create(groups);
    }

    @Override
    public boolean update(Groups groups){

        if (groups == null || groups.getGroupId() == null || groups.getGroupId() <= 0L) {
            throw new InvalidDataException("ID inválido");
        }

        if (!groupRepository.existsById(groups.getGroupId())) {
            throw new NotFoundException("Grupo no encontrado");
        }

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
