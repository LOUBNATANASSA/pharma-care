package com.pharma.backend.presentation.dto;

public record LoginDTO(
        Long id,
        String username,
        String email,
        String password,
        String type
) {}
