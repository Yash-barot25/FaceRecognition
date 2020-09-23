package com.stealth.yash.FaceRecognition.service.springdatajpa;

import com.stealth.yash.FaceRecognition.model.Institute;
import com.stealth.yash.FaceRecognition.repository.InstituteRepository;
import com.stealth.yash.FaceRecognition.service.InstituteService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class InstituteSDJpaService implements InstituteService {
    
    private final InstituteRepository instituteRepository;

    public InstituteSDJpaService(InstituteRepository instituteRepository) {
        this.instituteRepository = instituteRepository;
    }

    @Override
    public Set<Institute> findAll() {
        return new HashSet<>(instituteRepository.findAll());
    }

    @Override
    public Institute findById(Long aLong) {
        return instituteRepository.findById(aLong).orElse(null);
    }

    @Override
    public Institute save(Institute object) {
        return instituteRepository.save(object);
    }

    @Override
    public void delete(Institute object) {
        instituteRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        instituteRepository.deleteById(aLong);

    }
}
