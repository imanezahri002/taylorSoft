package org.taylorsoft.taylorsoft.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tissu_color")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TissuColor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String photo;

    @Column(nullable = false)
    private Double quantity;

    private boolean active;

    // Foreign key to Couleur
    @ManyToOne
    @JoinColumn(name = "couleur_id", nullable = false)
    private Couleur couleur;

    // Foreign key to TypeTissu
    @ManyToOne
    @JoinColumn(name = "tissu_id", nullable = false)
    private Tissu tissu;

    // Foreign key to Fournisseur
    @ManyToOne
    @JoinColumn(name = "fournisseur_id", nullable = false)
    private Fournisseur fournisseur;
}
