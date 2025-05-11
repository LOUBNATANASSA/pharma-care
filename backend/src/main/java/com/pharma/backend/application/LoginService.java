package com.pharma.backend.application;

import com.pharma.backend.domain.Login;
import com.pharma.backend.infrastructure.persistence.LoginRepository;
import com.pharma.backend.presentation.dto.LoginDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginService {

    private final LoginRepository loginRepository;

    public LoginService(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    public List<Login> getAllLogins() {
        return loginRepository.findAll();
    }

    public Login getLoginById(Long id) {
        return loginRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Login avec l'id " + id + " introuvable")
        );
    }

    public Login createLogin(LoginDTO dto) {
        Login login = new Login();
        login.setUsername(dto.username());
        login.setEmail(dto.email());
        login.setPassword(dto.password());
        login.setType(dto.type());
        return loginRepository.save(login);
    }

    public Login updateLogin(Long id, LoginDTO dto) {
        Login login = loginRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Login avec l'id " + id + " introuvable")
        );
        login.setUsername(dto.username());
        login.setEmail(dto.email());
        login.setPassword(dto.password());
        login.setType(dto.type());
        return loginRepository.save(login);
    }

    public void deleteLogin(Long id) {
        loginRepository.deleteById(id);
    }
}
