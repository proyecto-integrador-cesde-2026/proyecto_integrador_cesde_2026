package co.edu.cesde.ga.application.service.impl;

import co.edu.cesde.ga.domain.model.exceptions.DuplicateException;
import co.edu.cesde.ga.domain.model.exceptions.InvalidDataException;
import co.edu.cesde.ga.domain.model.exceptions.NotFoundException;
import co.edu.cesde.ga.domain.model.Student;
import co.edu.cesde.ga.domain.model.port.in.StudentService;
import co.edu.cesde.ga.domain.model.port.out.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student create(Student student){

        if (isInvalidStudent(student)) {
            throw new InvalidDataException(
                    "El estudiante contiene datos inválidos. Verifica el código, documento, nombre, apellido y estado."
            );
        }
        return studentRepository.create(student);
    }


    @Override
    public boolean update(Student studentUpdate) {

        if (studentUpdate == null
                || studentUpdate.getStudentId() == null
                || studentUpdate.getStudentId() <= 0L) {
            throw new InvalidDataException("ID inválido");
        }

        Student existing = studentRepository.findById(
                studentUpdate.getStudentId()
        );

        if (existing == null) {
            throw new NotFoundException(
                    "Estudiante no encontrado con ID: "
                            + studentUpdate.getStudentId()
            );
        }

        if (isInvalidStudent(studentUpdate)) {
            throw new InvalidDataException(
                    "El estudiante contiene datos inválidos. "
                            + "Verifica el código, documento, nombre, apellido y estado."
            );
        }

        Student duplicate = studentRepository.findByDocumentNumber(
                studentUpdate.getDocumentNumber()
        );

        if (duplicate != null
                && !duplicate.getStudentId().equals(studentUpdate.getStudentId())) {
            throw new DuplicateException(
                    "Documento ya pertenece a otro estudiante"
            );
        }

        return studentRepository.update(studentUpdate);
    }



    @Override
    public boolean delete(Long studentId){

        if (studentId == null || studentId <= 0L) {
            throw new InvalidDataException("ID inválido");
        }

        Student student = studentRepository.findById(studentId);

        if (student == null) {
            throw new NotFoundException("Estudiante no encontrado con el ID: " + studentId);
        }

        return studentRepository.delete(studentId);
    }
    @Override
    public Student findById(Long studentId){

        if (studentId == null || studentId <= 0L) {
            throw new InvalidDataException("ID inválido");
        }

        Student student = studentRepository.findById(studentId);

        if (student == null) {
            throw new NotFoundException("Estudiante no encontrado con el ID: " + studentId);
        }

        return student;
    }

    @Override
    public List<Student> findAll(){
        return studentRepository.findAll();
    }

    public boolean isInvalidStudent(Student student) {

        return student == null
                || !isNotBlank(student.getCode())
                || !isNotBlank(student.getDocumentNumber())
                || !isNotBlank(student.getFirstName())
                || !isNotBlank(student.getLastName())
                || !isValidStatus(student.getStatus());
    }

    private boolean isNotBlank(String value) {
        return value != null && !value.trim().isEmpty();
    }

    private boolean isValidStatus(String status) {
        return "ACTIVE".equals(status)
                || "INACTIVE".equals(status);
    }

}
