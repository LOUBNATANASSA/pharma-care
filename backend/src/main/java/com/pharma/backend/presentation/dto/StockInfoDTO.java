package com.pharma.backend.presentation.dto;

import java.time.LocalDate;

public record StockInfoDTO(
    String matricule,
    int quantiteStock,
    LocalDate date,
    int lotId
) {}