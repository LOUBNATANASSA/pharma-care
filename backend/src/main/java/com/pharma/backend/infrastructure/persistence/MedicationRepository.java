package com.pharma.backend.infrastructure.persistence;

import com.pharma.backend.domain.Medication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicationRepository extends JpaRepository<Medication, Long> {}
