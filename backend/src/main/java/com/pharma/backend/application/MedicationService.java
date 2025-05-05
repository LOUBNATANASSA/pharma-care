package com.pharma.backend.application;

import com.pharma.backend.domain.Medication;
import com.pharma.backend.infrastructure.persistence.MedicationRepository;
import com.pharma.backend.presentation.dto.MedicationDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicationService {

    private final MedicationRepository repo;

    public MedicationService(MedicationRepository repo) {
        this.repo = repo;
    }

    public List<Medication> findAll() {
        return repo.findAll();
    }

    public Medication findById(Long id) {
        return repo.findById(id).orElseThrow();
    }

    public Medication save(MedicationDTO dto) {
        Medication m = new Medication();
        m.setName(dto.name());
        m.setReference(dto.reference());
        m.setPrice(dto.price());
        m.setDosage(dto.dosage());
        m.setSupplier(dto.supplier());
        m.setForm(dto.form());
        m.setStorage(dto.storage());
        return repo.save(m);
    }
    public Medication update(Long id, MedicationDTO dto) {
    Medication m = repo.findById(id).orElseThrow();
    m.setName(dto.name());
    m.setReference(dto.reference());
    m.setPrice(dto.price());
    m.setDosage(dto.dosage());
    m.setSupplier(dto.supplier());
    m.setForm(dto.form());
    m.setStorage(dto.storage());
    return repo.save(m);
}


    public void delete(Long id) {
        repo.deleteById(id);
    }
}
