package com.pharma.backend.infrastructure.persistence;

import com.pharma.backend.domain.Vente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface VenteRepository extends JpaRepository<Vente, Long> {
    @Query("SELECT SUM(v.total) FROM Vente v WHERE v.date >= :date")
    Float sumTotalByDateAfter(@Param("date") LocalDate date);

}