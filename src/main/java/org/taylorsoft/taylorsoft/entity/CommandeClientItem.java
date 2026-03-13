package org.taylorsoft.taylorsoft.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entité représentant un item (ligne) dans une commande client
 */
@Entity
@Table(name = "commande_client_item")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommandeClientItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double quantite;

    @Column(name = "prix_unitaire", nullable = false)
    private Double prixUnitaire;

    @ManyToOne
    @JoinColumn(name = "model_color_id", nullable = false)
    private ModelColor modelColor;

    @ManyToOne
    @JoinColumn(name = "commande_client_id", nullable = false)
    private CommandeClient commandeClient;
}

