package com.pharma.backend.domain;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "vente")
public class Vente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private int quantiteVendue;

    @Column(nullable = false)
    private float total;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medication_id", nullable = false)
    private Medication medication;

    // Constructeurs
    public Vente() {}

    public Vente(Date date, int quantiteVendue, Medication medication) {
        this.date = date;
        this.quantiteVendue = quantiteVendue;
        this.medication = medication;
        this.calculerTotal(); // Calcule automatique du total
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getQuantiteVendue() {
        return quantiteVendue;
    }

    public void setQuantiteVendue(int quantiteVendue) {
        this.quantiteVendue = quantiteVendue;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public Medication getMedication() {
        return medication;
    }

    public void setMedication(Medication medication) {
        this.medication = medication;
    }

    // Méthode métier
    public void calculerTotal() {
        if (medication != null) {
            this.total = (float) (quantiteVendue * medication.getPrice());
        }
    }
}