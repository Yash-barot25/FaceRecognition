package com.stealth.yash.FaceRecognition.service.springdatajpa;
import com.stealth.yash.FaceRecognition.exception.NotFoundException;
import com.stealth.yash.FaceRecognition.model.AccessKey;
import com.stealth.yash.FaceRecognition.repository.AccessRepository;
import com.stealth.yash.FaceRecognition.service.AccessService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AccessSDJpaService implements AccessService {

    private final AccessRepository accessRepository;
    public AccessSDJpaService(AccessRepository accessRepository) {
        this.accessRepository = accessRepository;
    }


    @Override
    public Set<AccessKey> findAll() {
        return new HashSet<>(accessRepository.findAll());
    }

    @Override
    public AccessKey findById(Long aLong) {
        Optional<AccessKey> accessKeyOptional = accessRepository.findById(aLong);

        if (accessKeyOptional.isPresent()){
            return accessKeyOptional.get();
        }
        throw new NotFoundException("Access ID: " + aLong + " Was Not Found.");
    }



    @Override
    public AccessKey save(AccessKey object) {
        return accessRepository.save(object);
    }

    @Override
    public void delete(AccessKey object) {
        accessRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        accessRepository.findById(aLong);
    }

}
