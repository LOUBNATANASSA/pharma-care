package com.pharma.backend.presentation.dto;

public record MedicationDTO(

        String name,
        String reference,
        double price,
        String dosage,
        String supplier,
        String form,
        String storage,
        LotDTO lot,
        String etat_stock
) {}
