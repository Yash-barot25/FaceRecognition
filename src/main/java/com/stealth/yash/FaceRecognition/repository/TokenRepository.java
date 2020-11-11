package com.stealth.yash.FaceRecognition.repository;

import com.stealth.yash.FaceRecognition.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, Long> {
    Token findByConfirmationToken(String confirmationToken);


}
