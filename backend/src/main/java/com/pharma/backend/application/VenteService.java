package com.pharma.backend.application;

import com.pharma.backend.domain.Medication;

import com.pharma.backend.infrastructure.persistence.MedicationRepository;
import com.pharma.backend.presentation.dto.MedicationDTO;
import org.springframework.stereotype.Service;
import com.pharma.backend.domain.Vente;
import com.pharma.backend.presentation.dto.VenteDTO;
import com.pharma.backend.infrastructure.persistence.VenteRepository;
import java.util.Date;
import java.util.List;

@Service
public class VenteService {

    private final VenteRepository venteRepo;
    private final MedicationRepository medicationRepo;

    public VenteService(VenteRepository venteRepo, MedicationRepository medicationRepo) {
        this.venteRepo = venteRepo;
        this.medicationRepo = medicationRepo;
    }

    public List<Vente> findAll() {
        return venteRepo.findAll();
    }

    public Vente findById(Long id) {
        return venteRepo.findById(id).orElseThrow();
    }

    public Vente save(VenteDTO dto) {
        Medication medication = medicationRepo.findById(dto.medicationId()).orElseThrow();

        Vente vente = new Vente();
        vente.setDate(new Date()); // ou dto.date() si vous utilisez la date depuis le DTO
        vente.setQuantiteVendue(dto.quantiteVendue());
        vente.setMedication(medication);
        vente.calculerTotal();

        return venteRepo.save(vente);
    }

    public void delete(Long id) {
        venteRepo.deleteById(id);
    }
}