package com.stealth.yash.FaceRecognition.service.springdatajpa;

import com.stealth.yash.FaceRecognition.model.Program;
import com.stealth.yash.FaceRecognition.repository.ProgramRepository;
import com.stealth.yash.FaceRecognition.service.ProgramService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ProgramSDJpaService implements ProgramService {

    private final ProgramRepository programRepository;

    public ProgramSDJpaService(ProgramRepository programRepository) {
        this.programRepository = programRepository;
    }

    @Override
    public Set<Program> findAll() {
        return new HashSet<>(programRepository.findAll());
    }

    @Override
    public Program findById(Long aLong) {
        return programRepository.findById(aLong).orElse(null);
    }

    @Override
    public Program save(Program object) {
        return programRepository.save(object);
    }

    @Override
    public void delete(Program object) {
        programRepository.delete(object);

    }

    @Override
    public void deleteById(Long aLong) {
        programRepository.deleteById(aLong);
    }

    @Override
    public Set<Program> findProgramByDepartmentId(Long departmentId) {
        return programRepository.findProgramByDepartmentId(departmentId);
    }
}
