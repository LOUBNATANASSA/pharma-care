package com.pharma.backend.presentation.controller;

import com.pharma.backend.application.LoginService;
import com.pharma.backend.domain.Login;
import com.pharma.backend.presentation.dto.LoginDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/logins")
@CrossOrigin(origins = "*") // à modifier si tu veux limiter le frontend autorisé
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping
    public ResponseEntity<List<Login>> getAllLogins() {
        List<Login> logins = loginService.getAllLogins();
        return ResponseEntity.ok(logins);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getLoginById(@PathVariable Long id) {
        try {
            Login login = loginService.getLoginById(id);
            return ResponseEntity.ok(login);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Login non trouvé avec l'ID : " + id);
        }
    }

    @PostMapping
    public ResponseEntity<?> createLogin(@RequestBody LoginDTO dto) {
        try {
            Login login = loginService.createLogin(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(login);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erreur lors de la création du login");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateLogin(@PathVariable Long id, @RequestBody LoginDTO dto) {
        try {
            Login login = loginService.updateLogin(id, dto);
            return ResponseEntity.ok(login);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Impossible de modifier : login non trouvé");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLogin(@PathVariable Long id) {
        try {
            loginService.deleteLogin(id);
            return ResponseEntity.ok("Login supprimé avec succès");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la suppression du login");
        }
    }
}
