/**
 ************************** FACIAL RECOGNITION - CAPSTONE ************************
 * DepartmentSDJpaService
 *
 *  @author  STEALTH
 *
 */
package com.stealth.yash.FaceRecognition.service.springdatajpa;

import com.stealth.yash.FaceRecognition.exception.NotFoundException;
import com.stealth.yash.FaceRecognition.model.Department;
import com.stealth.yash.FaceRecognition.repository.DepartmentRepository;
import com.stealth.yash.FaceRecognition.service.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
//indicates that this class is a service
@Service
public class DepartmentSDJpaService implements DepartmentService {
    // declaring a departmentRepository
    private final DepartmentRepository departmentRepository;

    /** constructor
     * @param departmentRepository repository
     */
    public DepartmentSDJpaService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    /**
     * find all departments from repository
     * @return set containing departments
     */
    @Override
    public Set<Department> findAll() {
        return new HashSet<>(departmentRepository.findAll());
    }

    /**
     * find department by indicated departmentID else throw exception
     * @param aLong parameter of type Long
     * @return courses that may or may not exist
     */
    @Override
    public Department findById(Long aLong) {

        Optional<Department> optional = departmentRepository.findById(aLong);

        if (optional.isPresent()){
            return optional.get();
        }
        throw new NotFoundException("Department ID: " + aLong + " Was Not Found.");
    }

    /**
     * Saving department to the repository
     * @param object a department object
     * @return the saved department
     */

    @Override
    public Department save(Department object) {
        return departmentRepository.save(object);
    }


    /**
     * delete indicated department from the repository
     * @param object department object
     *
     */
    @Override
    public void delete(Department object) {
        departmentRepository.delete(object);
    }

    /**
     * delete department indicated by departmentId  from the repository
     * @param aLong object of type long
     *
     */
    @Override
    public void deleteById(Long aLong) {
        departmentRepository.deleteById(aLong);

    }
}
