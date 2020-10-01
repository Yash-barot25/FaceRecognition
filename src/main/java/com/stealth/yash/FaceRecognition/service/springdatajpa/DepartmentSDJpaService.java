package com.stealth.yash.FaceRecognition.service.springdatajpa;

import com.stealth.yash.FaceRecognition.exception.NotFoundException;
import com.stealth.yash.FaceRecognition.model.Department;
import com.stealth.yash.FaceRecognition.repository.DepartmentRepository;
import com.stealth.yash.FaceRecognition.service.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class DepartmentSDJpaService implements DepartmentService {
    
    private final DepartmentRepository departmentRepository;

    public DepartmentSDJpaService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Set<Department> findAll() {
        return new HashSet<>(departmentRepository.findAll());
    }

    @Override
    public Department findById(Long aLong) {

        Optional<Department> optional = departmentRepository.findById(aLong);

        if (optional.isPresent()){
            return optional.get();
        }
        throw new NotFoundException("Department ID: " + aLong + " Was Not Found.");
    }

    @Override
    public Department save(Department object) {
        return departmentRepository.save(object);
    }

    @Override
    public void delete(Department object) {
        departmentRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        departmentRepository.deleteById(aLong);

    }
}
