/**
 ************************** FACIAL RECOGNITION - CAPSTONE ************************
 * InstituteSDJpaService
 *
 *  @author  STEALTH
 *
 */
package com.stealth.yash.FaceRecognition.service.springdatajpa;

import com.stealth.yash.FaceRecognition.exception.NotFoundException;
import com.stealth.yash.FaceRecognition.model.Institute;
import com.stealth.yash.FaceRecognition.repository.InstituteRepository;
import com.stealth.yash.FaceRecognition.service.InstituteService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
//indicates that this class is a service
@Service
public class InstituteSDJpaService implements InstituteService {

    // declaring a InstituteRepository
    private final InstituteRepository instituteRepository;

    /** constructor
     * @param instituteRepository repository
     */
    public InstituteSDJpaService(InstituteRepository instituteRepository) {
        this.instituteRepository = instituteRepository;
    }

    /**
     * find all Institutes from repository
     * @return set containing institutes
     */
    @Override
    public Set<Institute> findAll() {
        return new HashSet<>(instituteRepository.findAll());
    }

    /**
     * find institute by indicated instituteID else throw exception
     * @param aLong parameter of type Long
     * @return institute that may or may not exist
     */
    @Override
    public Institute findById(Long aLong) {

        Optional<Institute> instituteOptional = instituteRepository.findById(aLong);

        if (instituteOptional.isPresent()){
            return instituteOptional.get();
        }
        throw new NotFoundException("Institute ID: " + aLong + " Was Not Found.");

    }

    /**
     * Saving institute to the repository
     * @param object a intitute object
     * @return the saved institute
     */
    @Override
    public Institute save(Institute object) {
        return instituteRepository.save(object);
    }

    /**
     * delete indicated institute from the repository
     * @param object Institute object
     *
     */
    @Override
    public void delete(Institute object) {
        instituteRepository.delete(object);
    }

    /**
     * delete institute indicated by instituteId  from the repository
     * @param aLong object of type long
     *
     */
    @Override
    public void deleteById(Long aLong) {
        instituteRepository.deleteById(aLong);

    }
}
