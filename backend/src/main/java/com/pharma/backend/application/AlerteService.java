package com.pharma.backend.application;
import com.pharma.backend.domain.Alerte;
import com.pharma.backend.infrastructure.persistence.AlerteRepository;

import com.pharma.backend.presentation.dto.AlerteDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlerteService {
    private final AlerteRepository alerteRepository;

    public AlerteService(AlerteRepository alerteRepository) {
        this.alerteRepository = alerteRepository;
    }

    // Constructeur inchangé
    public List<AlerteDTO> getAllAlertes() {
        return alerteRepository.findAll()
                .stream()
                .map(AlerteDTO::new) // Changé de AlerteRecord à AlerteDTO
                .toList();
    }

    public AlerteDTO getAlerteById(Long id) {
        return alerteRepository.findById(id)
                .map(AlerteDTO::new) // Changé ici
                .orElseThrow(() -> new RuntimeException("Alerte non trouvée"));
    }

    public AlerteDTO createAlerte(AlerteDTO alerteDTO) { // Type changé
        Alerte alerte = new Alerte();
        alerte.setType(alerteDTO.type());
        alerte.setDate(alerteDTO.date());

        Alerte savedAlerte = alerteRepository.save(alerte);
        return new AlerteDTO(savedAlerte); // Adaptation ici
    }

    public AlerteDTO updateAlerte(Long id, AlerteDTO alerteRecord) {
        Alerte alerte = alerteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alerte non trouvée"));

        alerte.setType(alerteRecord.type());
        alerte.setDate(alerteRecord.date());

        Alerte updatedAlerte = alerteRepository.save(alerte);
        return new AlerteDTO(updatedAlerte);
    }

    public void deleteAlerte(Long id) {
        alerteRepository.deleteById(id);
    }
}
