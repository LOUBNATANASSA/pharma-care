package com.pharma.backend.infrastructure.persistence;

import com.pharma.backend.domain.Lot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LotRepository extends JpaRepository<Lot, Integer> {
}