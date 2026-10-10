
package co.edu.cesde.ga.infrastructure.adapter.out.persistence.inmemory;

import co.edu.cesde.ga.domain.model.Student;
import co.edu.cesde.ga.domain.model.port.out.StudentRepository;
import co.edu.cesde.ga.infrastructure.adapter.out.persistence.jpa.StudentJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentRepositoryInMemory implements StudentRepository {

    private final StudentJpaRepository studentJpaRepository;

    public StudentRepositoryInMemory(StudentJpaRepository studentJpaRepository) {
        this.studentJpaRepository = studentJpaRepository;
    }

    @Override
    public Student create(Student student) {
        if (student == null) {
            return null;
        }
        return studentJpaRepository.save(student);
    }

    @Override
    public boolean existsByDocumentNumber(String documentNumber) {
        return studentJpaRepository.existsByDocumentNumber(documentNumber);
    }

    @Override
    public Student findByDocumentNumber(String documentNumber) {
        return studentJpaRepository.findByDocumentNumber(documentNumber);
    }

    @Override
    public List<Student> findAll() {
        return studentJpaRepository.findAll();
    }

    @Override
    public Student findById(Long studentId) {
        return studentJpaRepository.findById(studentId).orElse(null);
    }

    @Override
    public boolean delete(Long studentId) {
        if (!studentJpaRepository.existsById(studentId)) {
            return false;
        }
        studentJpaRepository.deleteById(studentId);
        return true;
    }

    @Override
    public int count() {
        return (int) studentJpaRepository.count();
    }

    @Override
    public boolean update(Student student) {
        if (student == null || student.getStudentId() == null
                || !studentJpaRepository.existsById(student.getStudentId())) {
            return false;
        }
        studentJpaRepository.save(student);
        return true;
    }
}