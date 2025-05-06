package com.pharma.backend.application;

import com.pharma.backend.domain.StockMovement;
import com.pharma.backend.infrastructure.persistence.StockMovementRepository;
import com.pharma.backend.presentation.dto.StockMovementDTO;
import com.pharma.backend.domain.Medication;
import com.pharma.backend.infrastructure.persistence.MedicationRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockMovementService {

    private final StockMovementRepository stockMovementRepository;
    private final MedicationRepository medicationRepository;

    public StockMovementService(StockMovementRepository stockMovementRepository, MedicationRepository medicationRepository) {
        this.stockMovementRepository = stockMovementRepository;
        this.medicationRepository = medicationRepository;
    }

    public List<StockMovement> getAllMovements() {
        return stockMovementRepository.findAll();
    }

    public StockMovement getMovementById(Long id) {
        return stockMovementRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Mouvement avec l'id " + id + " introuvable"));
    }

    public StockMovement createMovement(StockMovementDTO dto) {
        Medication medicament = medicationRepository.findById(dto.medicamentId())
                .orElseThrow(() -> new IllegalArgumentException("Médicament introuvable avec l'id " + dto.medicamentId()));

        StockMovement movement = new StockMovement();
        movement.setType(dto.type());
        movement.setDate(dto.date());
        movement.setQuantite(dto.quantite());
        movement.setDateexpiration(dto.dateExpiration());
        movement.setCommentaire(dto.commentaire());
        movement.setMedicament(medicament); // this will auto-set nomMedicament & reference via @PrePersist

        return stockMovementRepository.save(movement);
    }

    public StockMovement updateMovement(Long id, StockMovementDTO dto) {
        StockMovement movement = stockMovementRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Mouvement avec l'id " + id + " introuvable"));

        Medication medicament = medicationRepository.findById(dto.medicamentId())
                .orElseThrow(() -> new IllegalArgumentException("Médicament introuvable avec l'id " + dto.medicamentId()));

        movement.setType(dto.type());
        movement.setDate(dto.date());
        movement.setQuantite(dto.quantite());
        movement.setDateexpiration(dto.dateExpiration());
        movement.setCommentaire(dto.commentaire());
        movement.setMedicament(medicament); // will re-update copied fields if desired

        return stockMovementRepository.save(movement);
    }

    public void deleteMovement(Long id) {
        stockMovementRepository.deleteById(id);
    }
}
