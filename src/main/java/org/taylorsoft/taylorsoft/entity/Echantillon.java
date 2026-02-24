package org.taylorsoft.taylorsoft.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Echantillon")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Echantillon {
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

    // Foreign key to Categorie
    @ManyToOne
    @JoinColumn(name = "categorie_id", nullable = false)
    private Categorie categorie;

    // Foreign key to Coutourier
    @ManyToOne
    @JoinColumn(name = "coutourier_id", nullable = false)
    private Coutourier coutourier;
}
