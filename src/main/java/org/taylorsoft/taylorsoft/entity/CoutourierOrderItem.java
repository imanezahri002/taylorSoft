package org.taylorsoft.taylorsoft.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "coutourier_order_items")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoutourierOrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double nombreMetres;

    @Column(nullable = false)
    private Double prixTotalMetres;

    // Foreign key vers CoutourierOrder
    @ManyToOne
    @JoinColumn(name = "coutourier_order_id", nullable = false)
    private CoutourierOrder coutourierOrder;

    // Foreign key vers TissuColor
    @ManyToOne
    @JoinColumn(name = "tissu_color_id", nullable = false)
    private TissuColor tissuColor;
}

