package com.pharma.backend.infrastructure.persistence;

import com.pharma.backend.domain.Vente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VenteRepository extends JpaRepository<Vente, Long> {
}