package com.pharma.backend.application;

import com.pharma.backend.domain.Login;
import com.pharma.backend.infrastructure.persistence.SigninRepository;
import com.pharma.backend.presentation.dto.SigninDTO;
import org.springframework.stereotype.Service;

@Service
public class SigninService {

    private final SigninRepository signinRepository;

    public SigninService(SigninRepository signinRepository) {
        this.signinRepository = signinRepository;
    }

    public Login signin(SigninDTO dto) {
        return signinRepository.findByUsernameAndPassword(dto.username(), dto.password())
                .orElseThrow(() -> new IllegalArgumentException("Nom d'utilisateur ou mot de passe incorrect"));
    }
}
