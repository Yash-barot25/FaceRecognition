/**
 ************************** FACIAL RECOGNITION - CAPSTONE ************************
 * StudentSDJpaService
 *
 *  @author  STEALTH
 *
 */
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
//indicates that this class is a service
@Service
public class StudentSDJpaService implements StudentService {

    // declaring a studentRepository
    private final StudentRepository studentRepository;

    /** constructor
     * @param studentRepository repository
     */
   public StudentSDJpaService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    /**
     * return student by indicated lastName
     * @param lastName parameter of type String
     * @return student
     */
    @Override
    public Student findByLastName(String lastName) {
        return studentRepository.findByLastName(lastName);
    }

    /**
     * return list of student by indicated lastName
     * @param lastName parameter of type String
     * @return list of student
     */
    @Override
    public List<Student> findAllByLastNameLike(String lastName) {
        return studentRepository.findAllByLastNameLike(lastName);
    }
    @Override
    public Page<Student> findPaginated(int pageNo, int pageSize) {
        Pageable paging = PageRequest.of(pageNo - 1, pageSize);
        return studentRepository.findAll(paging);
    }

    /**
     * find all students from repository
     * @return set containing students
     */


    @Override
    public List<Student> searchStudent(String value) {
        return studentRepository.searchStudent(value);
    }

    @Override
    public Set<Student> findAll() {
        return new HashSet<>(studentRepository.findAll());
    }

    /**
     * find student by indicated studentID else throw exception
     * @param aLong parameter of type Long
     * @return students that may or may not exist
     */
    @Override
    public Student findById(Long aLong) {

        Optional<Student> optional = studentRepository.findById(aLong);

        if (optional.isPresent()){
            return optional.get();
        }
        throw new NotFoundException("Student ID: " + aLong + " Was Not Found.");
    }

    /**
     * Saving student to the repository
     * @param object a student object
     * @return the saved student
     */
    @Override
    public Student save(Student object) {
        return studentRepository.save(object);
    }

    /**
     * delete indicated student  from the repository
     * @param object Student object
     *
     */
    @Override
    public void delete(Student object) {
        studentRepository.delete(object);
    }

    /**
     * delete program indicated by studentId  from the repository
     * @param aLong object of type long
     *
     */
    @Override
    public void deleteById(Long aLong) {
        studentRepository.deleteById(aLong);
    }
}
