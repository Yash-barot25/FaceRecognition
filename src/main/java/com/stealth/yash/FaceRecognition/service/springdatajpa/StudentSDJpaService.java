package com.stealth.yash.FaceRecognition.service.springdatajpa;

import com.stealth.yash.FaceRecognition.exception.NotFoundException;
import com.stealth.yash.FaceRecognition.model.Student;
import com.stealth.yash.FaceRecognition.repository.StudentRepository;
import com.stealth.yash.FaceRecognition.service.StudentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class StudentSDJpaService implements StudentService {


    private final StudentRepository studentRepository;

   public StudentSDJpaService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student findByLastName(String lastName) {
        return studentRepository.findByLastName(lastName);
    }

    @Override
    public List<Student> findAllByLastNameLike(String lastName) {
        return studentRepository.findAllByLastNameLike(lastName);
    }

    @Override
    public Page<Student> findPaginated(int pageNo, int pageSize) {
        Pageable paging = PageRequest.of(pageNo - 1, pageSize);
        return studentRepository.findAll(paging);
    }

    @Override
    public Set<Student> findAll() {
        return new HashSet<>(studentRepository.findAll());
    }

    @Override
    public Student findById(Long aLong) {

        Optional<Student> optional = studentRepository.findById(aLong);

        if (optional.isPresent()){
            return optional.get();
        }
        throw new NotFoundException("Student ID: " + aLong + " Was Not Found.");
    }

    @Override
    public Student save(Student object) {
        return studentRepository.save(object);
    }

    @Override
    public void delete(Student object) {
        studentRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        studentRepository.deleteById(aLong);
    }
}
