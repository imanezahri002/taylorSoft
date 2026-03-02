package org.taylorsoft.taylorsoft.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "model")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100)
    private String nom;
    @Column(nullable = false, length = 100)
    private String description;
    @Column(nullable = false)
    private Double prix;
    @Column(nullable = false)
    boolean visibility;
    @Column(nullable=false)
    private Boolean active = true;

    // Foreign key to Categorie
    @ManyToOne
    @JoinColumn(name = "categorie_id", nullable = false)
    private Categorie categorie;

    // Foreign key to Coutourier
    @ManyToOne
    @JoinColumn(name = "coutourier_id", nullable = false)
    private Coutourier coutourier;

    // Foreign key to Tissu
    @ManyToOne
    @JoinColumn(name = "tissu_id", nullable = false)
    private Tissu tissu;

    // Relation avec ModelColor (un Model peut avoir plusieurs couleurs)
    @OneToMany(mappedBy = "model", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<ModelColor> modelColors = new ArrayList<>();
}

