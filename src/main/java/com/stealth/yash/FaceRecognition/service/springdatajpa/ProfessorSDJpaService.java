package com.stealth.yash.FaceRecognition.service.springdatajpa;

import com.stealth.yash.FaceRecognition.exception.NotFoundException;
import com.stealth.yash.FaceRecognition.model.Professor;
import com.stealth.yash.FaceRecognition.repository.ProfessorRepository;
import com.stealth.yash.FaceRecognition.service.ProfessorService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class ProfessorSDJpaService implements ProfessorService {

    private final ProfessorRepository professorRepository;

    public ProfessorSDJpaService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    @Override
    public Set<Professor> findAll() {
        return new HashSet<>(professorRepository.findAll());
    }

    @Override
    public Professor findById(Long aLong) {

        Optional<Professor> optional = professorRepository.findById(aLong);

        if (optional.isPresent()){
            return optional.get();
        }
        throw new NotFoundException("Professor ID: " + aLong + " Was Not Found.");
    }

    @Override
    public Professor save(Professor object) {
        return professorRepository.save(object);
    }

    @Override
    public void delete(Professor object) {
        professorRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        professorRepository.deleteById(aLong);
    }

    @Override
    public Set<Professor> findProfessorsByProgramId(Long id) {
        return professorRepository.findProfessorsByProgramId(id);
    }

    @Override
    public Set<Professor> findProfessorsByDepartmentId(Long id) {
        return professorRepository.findProfessorsByDepartmentId(id);
    }
}
