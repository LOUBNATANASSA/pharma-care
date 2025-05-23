package com.pharma.backend.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

@Entity
@Table(name = "medication", uniqueConstraints = @UniqueConstraint(columnNames = "reference"))
public class Medication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String reference;

    @Column(nullable = false)
    private double price;

    @Column(nullable = true)
    private String dosage;

    @Column(nullable = false)
    private String supplier;

    @Column(nullable = false)
    private String form;

    @Column(nullable = false)
    private String storage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lot_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Lot lot;


    @Column(nullable = false)
    private String etat_stock;



    public Medication() {}

    public Medication(Long id, String name, String reference, double price, String dosage, String supplier, String form, String storage, Lot lot, String etat_stock) {
        this.id = id;
        this.name = name;
        this.reference = reference;
        this.price = price;
        this.dosage = dosage;
        this.supplier = supplier;
        this.form = form;
        this.storage = storage;
        this.lot = lot;
        this.etat_stock = etat_stock;
    }

    @Transient
    public String getNomLot() {
        return lot != null ? lot.getNom_lot() : null;
    }

    public Lot getLot() {
        return lot;
    }

    public void setLot(Lot medLot) {
        this.lot = medLot;
    }

    public String getEtat_stock() {
        return etat_stock;
    }

    public void setEtat_stock(String etat_stock) {
        this.etat_stock = etat_stock;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }
}
