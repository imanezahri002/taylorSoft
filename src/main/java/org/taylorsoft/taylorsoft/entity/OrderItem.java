package org.taylorsoft.taylorsoft.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "order_item")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relation avec la commande
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    // Chaque item est lié à un TissuColorEchantillon
    @ManyToOne
    @JoinColumn(name = "tissu_color_echantillon_id", nullable = false)
    private TissuColor tissuColorEchantillon;

    @Column(nullable = false)
    private Double quantity;

    @Column(nullable = false)
    private Double price; // prix par cet item
}

