package com.pharma.backend.presentation.controller;
import com.pharma.backend.application.AlerteService;
import com.pharma.backend.domain.Alerte;
import com.pharma.backend.presentation.dto.AlerteDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// AlerteController.java
@RestController
@RequestMapping("/api/alertes")
public class AlerteController {
    private final AlerteService alerteService;

    public AlerteController(AlerteService alerteService) {
        this.alerteService = alerteService;
    }

    @GetMapping
    public ResponseEntity<List<AlerteDTO>> getAllAlertes() {
        return ResponseEntity.ok(alerteService.getAllAlertes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlerteDTO> getAlerteById(@PathVariable Long id) {
        return ResponseEntity.ok(alerteService.getAlerteById(id));
    }

    @PostMapping
    public ResponseEntity<AlerteDTO> createAlerte(@RequestBody AlerteDTO alerteRecord) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(alerteService.createAlerte(alerteRecord));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlerteDTO> updateAlerte(
            @PathVariable Long id,
            @RequestBody AlerteDTO alerteRecord
    ) {
        return ResponseEntity.ok(alerteService.updateAlerte(id, alerteRecord));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlerte(@PathVariable Long id) {
        alerteService.deleteAlerte(id);
        return ResponseEntity.noContent().build();
    }
}