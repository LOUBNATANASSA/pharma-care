package com.pharma.backend.presentation.controller;

import com.pharma.backend.application.StockMovementService;
import com.pharma.backend.presentation.dto.StockMovementDTO;
import com.pharma.backend.domain.StockMovement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stock-movements")
public class StockMovementController {

    private final StockMovementService stockMovementService;

    public StockMovementController(StockMovementService stockMovementService) {
        this.stockMovementService = stockMovementService;
    }

    // Récupérer tous les mouvements de stock
    @GetMapping
    public ResponseEntity<List<StockMovement>> getAllStockMovements() {
        List<StockMovement> stockMovements = stockMovementService.getAllMovements();
        return new ResponseEntity<>(stockMovements, HttpStatus.OK);
    }

    // Récupérer un mouvement de stock par ID
    @GetMapping("/{id}")
    public ResponseEntity<StockMovement> getStockMovementById(@PathVariable Long id) {
        StockMovement stockMovement = stockMovementService.getMovementById(id);
        return new ResponseEntity<>(stockMovement, HttpStatus.OK);
    }

    // Créer un nouveau mouvement de stock
    @PostMapping
    public ResponseEntity<StockMovement> createStockMovement(@RequestBody StockMovementDTO dto) {
        StockMovement stockMovement = stockMovementService.createMovement(dto);
        return new ResponseEntity<>(stockMovement, HttpStatus.CREATED);
    }

    // Mettre à jour un mouvement de stock existant
    @PutMapping("/{id}")
    public ResponseEntity<StockMovement> updateStockMovement(@PathVariable Long id, @RequestBody StockMovementDTO dto) {
        StockMovement stockMovement = stockMovementService.updateMovement(id, dto);
        return new ResponseEntity<>(stockMovement, HttpStatus.OK);
    }

    // Supprimer un mouvement de stock
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStockMovement(@PathVariable Long id) {
        stockMovementService.deleteMovement(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
