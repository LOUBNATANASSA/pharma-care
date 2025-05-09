package com.pharma.backend.presentation.dto;

public record VenteDTO(
    int quantiteVendue,
    Long medicationId
) {}