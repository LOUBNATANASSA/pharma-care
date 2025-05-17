package com.pharma.backend.presentation.controller;


import com.pharma.backend.domain.Login;
import com.pharma.backend.application.SigninService;
import com.pharma.backend.presentation.dto.SigninDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class SigninController {

    private final SigninService signinService;

    public SigninController(SigninService signinService) {
        this.signinService = signinService;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody SigninDTO dto) {
        try {
            Login user = signinService.signin(dto);
            return ResponseEntity.ok(user);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(401).body("Échec de connexion : identifiants incorrects");
        }
    }
}
