package co.edu.cesde.ga.infrastructure.adapter.out.persistence.inmemory;

import co.edu.cesde.ga.domain.model.Subject;
import co.edu.cesde.ga.domain.model.port.out.SubjectsRepository;
import co.edu.cesde.ga.infrastructure.adapter.out.persistence.jpa.SubjectJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SubjectsRepositoryInMemory implements SubjectsRepository {

    private final SubjectJpaRepository subjectJpaRepository;

    public SubjectsRepositoryInMemory(SubjectJpaRepository subjectJpaRepository) {
        this.subjectJpaRepository = subjectJpaRepository;
    }

    @Override
    public Subject create(Subject subject) {
        return subjectJpaRepository.save(subject);
    }

    @Override
    public boolean existsByCode(String code) {
        return subjectJpaRepository.existsByCode(code);
    }


    @Override
    public boolean existsById(Long subjectId) {
        return subjectJpaRepository.existsById(subjectId);
    }

    @Override
    public Subject findByCode(String code) {
        return subjectJpaRepository.findByCode(code);
    }

    @Override
    public List<Subject> findAll() {
        return subjectJpaRepository.findAll();
    }

    @Override
    public boolean delete(String code) {
        Subject subject = subjectJpaRepository.findByCode(code);

        if (subject == null) {
            return false;
        }

        subjectJpaRepository.delete(subject);
        return true;
    }

    @Override
    public int count() {
        return (int) subjectJpaRepository.count();
    }


    @Override
    public boolean update(Subject subject) {
        if (subject == null
                || subject.getSubjectId() == null
                || !subjectJpaRepository.existsById(subject.getSubjectId())) {
            return false;
        }

        subjectJpaRepository.save(subject);
        return true;
    }

}

