package com.pharma.backend.presentation.controller;

import com.pharma.backend.application.LotService;
import com.pharma.backend.domain.Lot;
import com.pharma.backend.presentation.dto.LotDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lots")
@CrossOrigin(origins = "*") // à modifier si tu veux limiter le frontend autorisé
public class LotController {

    private final LotService lotService;

    public LotController(LotService lotService) {
        this.lotService = lotService;
    }

    @GetMapping
    public ResponseEntity<List<Lot>> getAllLots() {
        List<Lot> lots = lotService.getAllLots();
        return ResponseEntity.ok(lots);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getLotById(@PathVariable int id) {
        try {
            Lot lot = lotService.getLotById(id);
            return ResponseEntity.ok(lot);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lot non trouvé avec l'ID : " + id);
        }
    }

    @PostMapping
    public ResponseEntity<?> createLot(@RequestBody LotDTO dto) {
        try {
            Lot lot = lotService.createLot(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(lot);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erreur lors de la création du lot");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateLot(@PathVariable int id, @RequestBody LotDTO dto) {
        try {
            Lot lot = lotService.updateLot(id, dto);
            return ResponseEntity.ok(lot);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Impossible de modifier : lot non trouvé");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLot(@PathVariable int id) {
        try {
            lotService.deleteLot(id);
            return ResponseEntity.ok("Lot supprimé avec succès");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la suppression du lot");
        }
    }
}
