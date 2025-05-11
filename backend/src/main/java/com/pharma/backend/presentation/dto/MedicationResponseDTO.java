package com.pharma.backend.presentation.dto;

// MedicationResponseDTO.java
public record MedicationResponseDTO(
    Long id,
    String name,
    String reference,
    double price,
    String dosage,
    String supplier,
    String form,
    String storage,
    String etat_stock,
    int   lotId,
    String lotName
) {}
