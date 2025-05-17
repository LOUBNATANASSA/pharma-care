package com.pharma.backend.domain;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Alerte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;
    private LocalDate date;

    // Constructeurs
    public Alerte() {}

    public Alerte(String type, LocalDate date) {
        this.type = type;
        this.date = date;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
