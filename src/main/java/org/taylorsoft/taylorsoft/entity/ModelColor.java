package org.taylorsoft.taylorsoft.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "model_color")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModelColor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Foreign key to Model
    @ManyToOne
    @JoinColumn(name = "model_id", nullable = false)
    private Model model;

    // Foreign key to Couleur (Color)
    @ManyToOne
    @JoinColumn(name = "couleur_id", nullable = false)
    private Couleur couleur;

    // Relation avec ModelPhoto (un ModelColor peut avoir plusieurs photos)
    @OneToMany(mappedBy = "modelColor", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<ModelPhoto> photos = new ArrayList<>();
}

