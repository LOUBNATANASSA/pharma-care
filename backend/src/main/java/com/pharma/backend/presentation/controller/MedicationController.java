package com.pharma.backend.presentation.controller;

import com.pharma.backend.application.MedicationService;
import com.pharma.backend.domain.Medication;
import com.pharma.backend.presentation.dto.MedicationDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medications")
@CrossOrigin(origins = "*")
public class MedicationController {

    private final MedicationService service;

    public MedicationController(MedicationService service) {
        this.service = service;
    }

    @GetMapping
    public List<Medication> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Medication getOne(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Medication create(@RequestBody MedicationDTO dto) {
        return service.save(dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @PutMapping("/{id}")
public Medication update(@PathVariable Long id, @RequestBody MedicationDTO dto) {
    return service.update(id, dto);
}
}
