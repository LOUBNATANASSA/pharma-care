package com.pharma.backend.presentation.dto;

import java.sql.Date;
import java.time.LocalDate;

public record StockMovementDTO(
        String type,
        Date date,
        int quantite,
        Date dateExpiration,
        String commentaire,
        Long medicamentId
) {}
