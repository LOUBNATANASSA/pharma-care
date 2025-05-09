package com.pharma.backend.presentation.controller;

import com.pharma.backend.application.MedicationService;
import com.pharma.backend.domain.Medication;
import com.pharma.backend.presentation.dto.MedicationDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.pharma.backend.application.VenteService;
import com.pharma.backend.domain.Vente;
import com.pharma.backend.presentation.dto.VenteDTO;
import java.util.List;

@RestController
@RequestMapping("/api/ventes")
@CrossOrigin(origins = "*")
public class VenteController {

    private final VenteService service;

    public VenteController(VenteService service) {
        this.service = service;
    }

    @GetMapping
    public List<Vente> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Vente getOne(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Vente create(@RequestBody VenteDTO dto) {
        return service.save(dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}