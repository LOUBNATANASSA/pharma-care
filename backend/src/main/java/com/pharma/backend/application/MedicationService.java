package com.pharma.backend.application;

import com.pharma.backend.domain.Lot;
import com.pharma.backend.domain.Medication;
import com.pharma.backend.infrastructure.persistence.LotRepository;
import com.pharma.backend.infrastructure.persistence.MedicationRepository;
import com.pharma.backend.presentation.dto.MedicationDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicationService {

    private final MedicationRepository repo;
    private final LotRepository lotRepo;

    public MedicationService(MedicationRepository repo, LotRepository lotRepo) {
        this.repo = repo;
        this.lotRepo = lotRepo;
    }

    public List<Medication> findAll() {
        return repo.findAll();
    }

    public Medication findById(Long id) {
        return repo.findById(id).orElseThrow();
    }

    public Medication save(MedicationDTO dto) {
        Lot lot = lotRepo.findById(dto.lot().lotId())
                .orElseThrow(() -> new RuntimeException("Lot not found"));

        Medication m = new Medication();
        m.setName(dto.name());
        m.setReference(dto.reference());
        m.setPrice(dto.price());
        m.setDosage(dto.dosage());
        m.setSupplier(dto.supplier());
        m.setForm(dto.form());
        m.setStorage(dto.storage());
        m.setEtat_stock(dto.etat_stock());
        m.setLot(lot);
        return repo.save(m);
    }



    public Medication update(Long id, MedicationDTO dto) {
        Medication m = repo.findById(id).orElseThrow();

        Lot lot = lotRepo.findById(dto.lot().lotId())
                .orElseThrow(() -> new RuntimeException("Lot not found"));

        m.setName(dto.name());
        m.setReference(dto.reference());
        m.setPrice(dto.price());
        m.setDosage(dto.dosage());
        m.setSupplier(dto.supplier());
        m.setForm(dto.form());
        m.setStorage(dto.storage());
        m.setEtat_stock(dto.etat_stock());
        m.setLot(lot); // ici, la variable 'lot' est bien définie plus haut

        return repo.save(m);
    }



    public void delete(Long id) {
        repo.deleteById(id);
    }
}
