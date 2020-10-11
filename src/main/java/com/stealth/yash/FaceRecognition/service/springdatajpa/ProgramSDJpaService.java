/**
 ************************** FACIAL RECOGNITION - CAPSTONE ************************
 * ProgramSDJpaService
 *
 *  @author  STEALTH
 *
 */
package com.stealth.yash.FaceRecognition.service.springdatajpa;

import com.stealth.yash.FaceRecognition.exception.NotFoundException;
import com.stealth.yash.FaceRecognition.model.Program;
import com.stealth.yash.FaceRecognition.repository.ProgramRepository;
import com.stealth.yash.FaceRecognition.service.ProgramService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
//indicates that this class is a service
@Service
public class ProgramSDJpaService implements ProgramService {

    // declaring a ProgramRepository
    private final ProgramRepository programRepository;

    /** constructor
     * @param programRepository repository
     */
    public ProgramSDJpaService(ProgramRepository programRepository) {
        this.programRepository = programRepository;
    }

    /**
     * find all programs from repository
     * @return set containing programs
     */
    @Override
    public Set<Program> findAll() {
        return new HashSet<>(programRepository.findAll());
    }

 /**
     * find program by indicated departmentID else throw exception
     * @param aLong parameter of type Long
     * @return program that may or may not exist
     */
    @Override
    public Program findById(Long aLong) {

        Optional<Program> optional = programRepository.findById(aLong);

        if (optional.isPresent()){
            return optional.get();
        }
        throw new NotFoundException("Program ID: " + aLong + " Was Not Found.");
    }

    /**
     * Saving program to the repository
     * @param object a program object
     * @return the saved program
     */
    @Override
    public Program save(Program object) {
        return programRepository.save(object);
    }

    /**
     * delete indicated program  from the repository
     * @param object Program object
     *
     */
    @Override
    public void delete(Program object) {
        programRepository.delete(object);

    }

    /**
     * delete program indicated by ProgramId  from the repository
     * @param aLong object of type long
     *
     */
    @Override
    public void deleteById(Long aLong) {
        programRepository.deleteById(aLong);
    }


    /**
     * find all programs from repository by departmentID
     * @param departmentId type Long
     * @return programs
     */
    @Override
    public Set<Program> findProgramByDepartmentId(Long departmentId) {
        return programRepository.findProgramByDepartmentId(departmentId);
    }
}
