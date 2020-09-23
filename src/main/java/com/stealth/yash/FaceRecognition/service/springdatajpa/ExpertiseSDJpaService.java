//package com.stealth.yash.FaceRecognition.service.springdatajpa;
//
//import com.stealth.yash.FaceRecognition.model.Experty;
//import com.stealth.yash.FaceRecognition.model.Professor;
//import com.stealth.yash.FaceRecognition.repository.ExpertiseRepository;
//import com.stealth.yash.FaceRecognition.repository.ProfessorRepository;
//import com.stealth.yash.FaceRecognition.service.ExpertiseService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//
//import java.util.HashSet;
//import java.util.Optional;
//import java.util.Set;
//
//@Slf4j
//@Service
//public class ExpertiseSDJpaService implements ExpertiseService {
//
//    private final ExpertiseRepository expertiseRepository;
//    private final ProfessorRepository professorRepository;
//
//
//    public ExpertiseSDJpaService(ExpertiseRepository expertiseRepository, ProfessorRepository professorRepository) {
//        this.expertiseRepository = expertiseRepository;
//        this.professorRepository = professorRepository;
//    }
//
//    @Override
//    public Set<Experty> findAll() {
//        return new HashSet<>(expertiseRepository.findAll());
//    }
//
//    @Override
//    public Experty findById(Long aLong) {
//        return expertiseRepository.findById(aLong).orElse(null);
//    }
//
//    @Override
//    public Experty save(Experty object) {
//        return expertiseRepository.save(object);
//    }
//
//    @Override
//    public void delete(Experty object) {
//        expertiseRepository.delete(object);
//    }
//
//    @Override
//    public void deleteById(Long aLong) {
//        expertiseRepository.deleteById(aLong);
//
//    }
//
////    @Override
////    public Experty findByProfessorIdAndExpertyID(Long professorId, Long expertyID) {
////        Optional<Professor> professorOptional = professorRepository.findById(professorId);
////
////        if (professorOptional.isEmpty()) {
////            log.error("Professor didn't found");
////        }
////
////        Professor professor = professorOptional.get();
////        Optional<Experty> expertyOptional = professor.getExperties().stream()
////                .filter(experty -> experty.getId().equals(expertyID)).findFirst();
////
////        return expertyOptional.get();
////
////
////    }
//}
