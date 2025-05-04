package com.pharma.backend.domain;

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

    public Medication() {}

    public Medication(Long id, String name, String reference, double price, String dosage, String supplier, String form, String storage) {
        this.id = id;
        this.name = name;
        this.reference = reference;
        this.price = price;
        this.dosage = dosage;
        this.supplier = supplier;
        this.form = form;
        this.storage = storage;
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
