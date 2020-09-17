package com.stealth.yash.FaceRecognition.service.springdatajpa;

import com.stealth.yash.FaceRecognition.model.Professor;
import com.stealth.yash.FaceRecognition.repository.ProfessorRepository;
import com.stealth.yash.FaceRecognition.service.CourseService;
import com.stealth.yash.FaceRecognition.service.ProfessorService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ProfessorSDJpaService implements ProfessorService {

    private final ProfessorRepository professorRepository;

    public ProfessorSDJpaService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    @Override
    public Set<Professor> findAll() {
        Set<Professor> professors = new HashSet<>();
        professorRepository.findAll().forEach(professors::add);
        return professors;
    }

    @Override
    public Professor findById(Long aLong) {
        return professorRepository.findById(aLong).orElse(null);
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
}
