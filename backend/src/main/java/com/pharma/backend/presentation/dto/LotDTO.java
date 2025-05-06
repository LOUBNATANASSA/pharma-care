package com.pharma.backend.presentation.dto;

import java.time.LocalDate;

public record LotDTO(
        int lotId,
        String Nom_lot,
        LocalDate dateReception,
        int maxQuantite,
        String type

) {}
