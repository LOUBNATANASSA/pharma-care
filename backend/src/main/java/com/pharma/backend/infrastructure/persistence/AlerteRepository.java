package com.pharma.backend.infrastructure.persistence;
import org.springframework.data.jpa.repository.JpaRepository;
import com.pharma.backend.domain.Alerte;

import java.util.List;

public interface AlerteRepository extends JpaRepository<Alerte, Long> {
    List<Alerte> findTop5ByOrderByDateDesc();

}