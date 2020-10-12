/**
 ************************** FACIAL RECOGNITION - CAPSTONE ************************
 * ProfessorSDJpaService
 *
 *  @author  STEALTH
 *
 */
package com.stealth.yash.FaceRecognition.service.springdatajpa;

import com.stealth.yash.FaceRecognition.exception.NotFoundException;
import com.stealth.yash.FaceRecognition.model.Professor;
import com.stealth.yash.FaceRecognition.repository.ProfessorRepository;
import com.stealth.yash.FaceRecognition.service.ProfessorService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
//indicates that this class is a service
@Service
public class ProfessorSDJpaService implements ProfessorService {

    // declaring a professorRepository
    private final ProfessorRepository professorRepository;

    /** constructor
     * @param professorRepository repository
     */
    public ProfessorSDJpaService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    /**
     * find all professors from repository
     * @return set containing professors
     */
    @Override
    public Set<Professor> findAll() {
        return new HashSet<>(professorRepository.findAll());
    }

    /**
     * find professor by indicated professorID else throw exception
     * @param aLong parameter of type Long
     * @return professor that may or may not exist
     */
    @Override
    public Professor findById(Long aLong) {

        Optional<Professor> optional = professorRepository.findById(aLong);

        if (optional.isPresent()){
            return optional.get();
        }
        throw new NotFoundException("Professor ID: " + aLong + " Was Not Found.");
    }

    /**
     * Saving Professor to the repository
     * @param object a professor object
     * @return the saved professor
     */
    @Override
    public Professor save(Professor object) {
        return professorRepository.save(object);
    }

    /**
     * delete professor indicated by professorId  from the repository
     * @param object Professor object
     *
     */
    @Override
    public void delete(Professor object) {
        professorRepository.delete(object);
    }
    /**
     * delete institute indicated by ProfessorId  from the repository
     * @param aLong object of type long
     *
     */
    @Override
    public void deleteById(Long aLong) {
        professorRepository.deleteById(aLong);
    }

    /**
     * find all professors from repository by programID
     * @param id type Long
     * @return  professors
     */
    @Override
    public Set<Professor> findProfessorsByProgramId(Long id) {
        return professorRepository.findProfessorsByProgramId(id);
    }
    /**
     * find all professors from repository by departmentID
     * @param id type Long
     * @return  professors
     */
    @Override
    public Set<Professor> findProfessorsByDepartmentId(Long id) {
        return professorRepository.findProfessorsByDepartmentId(id);
    }
}
