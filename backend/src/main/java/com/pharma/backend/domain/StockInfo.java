package com.pharma.backend.domain;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class StockInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String matricule;

    @Column(nullable = false)
    private int quantiteStock;

    @Column(nullable = false)
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lot_id", nullable = false)
    private Lot lot;

    public StockInfo() {}

    public StockInfo(String matricule, int quantiteStock, LocalDate date, Lot lot) {
        this.matricule = matricule;
        this.quantiteStock = quantiteStock;
        this.date = date;
        this.lot = lot;
    }

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getMatricule() { return matricule; }
    public void setMatricule(String matricule) { this.matricule = matricule; }

    public int getQuantiteStock() { return quantiteStock; }
    public void setQuantiteStock(int quantiteStock) { this.quantiteStock = quantiteStock; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public Lot getLot() { return lot; }
    public void setLot(Lot lot) { this.lot = lot; }
}
