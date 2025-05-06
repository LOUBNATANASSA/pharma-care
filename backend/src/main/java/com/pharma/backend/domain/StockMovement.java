package com.pharma.backend.domain;

import jakarta.persistence.*;
import com.pharma.backend.domain.Medication;
import java.sql.Date;
import java.time.LocalDate;

@Entity
@Table(name = "StockMovement", uniqueConstraints = @UniqueConstraint(columnNames = "reference"))

public class StockMovement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String type;
    @Column(nullable = false)// entree / sortie
    private Date date;
    @Column(nullable = false)
    private int quantite;
    @Column(nullable = false)
    private Date dateexpiration;
    @Column(nullable = false)
    private String commentaire;

    // Copie des infos du médicament (historisation)
    @Column(nullable = false)
    private String nomMedicament;
    @Column(nullable = false)
    private String referenceMedica;

    // Relation vers le médicament d’origine
    @ManyToOne
    @JoinColumn(name = "medicament_id", nullable = false)
    private Medication medicament;

    // Avant de sauvegarder, on copie les données du médicament
    @PrePersist
    public void prePersist() {
        if (medicament != null) {
            this.nomMedicament = medicament.getName();
            this.referenceMedica = medicament.getReference();
        }
    }
    public StockMovement() {}

    // Getters, setters

    public StockMovement(Long id, String type, Date date, int quantite, Date dateexpiration, String commentaire, String nomMedicament, String referenceMedica, Medication medicament) {
        this.id = id;
        this.type = type;
        this.date = date;
        this.quantite = quantite;
        this.dateexpiration = dateexpiration;
        this.commentaire = commentaire;
        this.nomMedicament = nomMedicament;
        this.referenceMedica = referenceMedica;
        this.medicament = medicament;
    }

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public Date getDateexpiration() {
        return dateexpiration;
    }

    public void setDateexpiration(Date dateexpiration) {
        this.dateexpiration = dateexpiration;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public String getNomMedicament() {
        return nomMedicament;
    }

    public void setNomMedicament(String nomMedicament) {
        this.nomMedicament = nomMedicament;
    }

    public String getReferenceMedica() {
        return referenceMedica;
    }

    public void setReferenceMedica(String referenceMedica) {
        this.referenceMedica = referenceMedica;
    }

    public Medication getMedicament() {
        return medicament;
    }

    public void setMedicament(Medication medicament) {
        this.medicament = medicament;
    }
}
