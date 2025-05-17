package com.pharma.backend.infrastructure.persistence;

import com.pharma.backend.domain.StockMovement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface StockMovementRepository extends JpaRepository<StockMovement,  Long> {
    long countByDateexpirationBefore(LocalDate date);

}
