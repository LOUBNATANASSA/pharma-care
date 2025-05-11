package com.pharma.backend.infrastructure.persistence;

import com.pharma.backend.domain.Login;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SigninRepository extends JpaRepository<Login, Long> {
    Optional<Login> findByUsernameAndPassword(String username, String password);
}
