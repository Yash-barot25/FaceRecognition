package com.stealth.yash.FaceRecognition.service.springdatajpa;

import com.stealth.yash.FaceRecognition.exception.NotFoundException;
import com.stealth.yash.FaceRecognition.model.Program;
import com.stealth.yash.FaceRecognition.repository.ProgramRepository;
import com.stealth.yash.FaceRecognition.service.ProgramService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ProgramSDJpaService implements ProgramService {

    private final ProgramRepository programRepository;

    public ProgramSDJpaService(ProgramRepository programRepository) {
        this.programRepository = programRepository;
    }

    @Override
    public Program findByProgramName(String name) {
        return programRepository.findByProgramName(name);
    }

    @Override
    public List<Program> findAllByProgramNameLike(String name) {
        return programRepository.findAllByProgramNameLike(name);
    }

    @Override
    public List<Program> searchProgram(String value) {
        return programRepository.searchProgram(value);
    }

    @Override
    public Set<Program> findAll() {
        return new HashSet<>(programRepository.findAll());
    }

    @Override
    public Program findById(Long aLong) {

        Optional<Program> optional = programRepository.findById(aLong);

        if (optional.isPresent()){
            return optional.get();
        }
        throw new NotFoundException("Program ID: " + aLong + " Was Not Found.");
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
