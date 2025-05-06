package com.pharma.backend.domain;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Lot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String Nom_lot;

    @Column(nullable = false)
    private LocalDate dateReception;

    @Column(nullable = false)
    private int maxQuantite;

    @Column(nullable = false)
    private String type;

    public Lot() {}

    public Lot(int id, String nom_lot, LocalDate dateReception, int maxQuantite, String type) {
        this.id = id;
        Nom_lot = nom_lot;
        this.dateReception = dateReception;
        this.maxQuantite = maxQuantite;
        this.type = type;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNom_lot() { return Nom_lot; }
    public void setNom_lot(String Nom_lot) { this.Nom_lot = Nom_lot; }

    public LocalDate getDateReception() { return dateReception; }
    public void setDateReception(LocalDate dateReception) { this.dateReception = dateReception; }

    public int getMaxQuantite() { return maxQuantite; }
    public void setMaxQuantite(int maxQuantite) { this.maxQuantite = maxQuantite; }
}
