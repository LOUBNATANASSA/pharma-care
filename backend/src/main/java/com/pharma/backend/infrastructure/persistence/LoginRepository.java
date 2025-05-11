package com.pharma.backend.infrastructure.persistence;

import com.pharma.backend.domain.Login;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<Login, Long> {
    // Vous pouvez ajouter des méthodes spécifiques de recherche si nécessaire
    Login findByUsername(String username);
    Login findByEmail(String email);
}
