package com.pharma.backend.presentation.dto;

import com.pharma.backend.domain.Alerte;

import java.time.LocalDate;

public record AlerteDTO(
        Long id,
        String type,
        LocalDate date
) {
    public AlerteDTO(Alerte alerte) {
        this(alerte.getId(), alerte.getType(), alerte.getDate());
    }
}
