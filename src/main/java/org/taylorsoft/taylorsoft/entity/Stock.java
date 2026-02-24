package org.taylorsoft.taylorsoft.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "stock")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tissu_color_id", nullable = false)
    private TissuColor tissuColor;

    @Column(nullable = false)
    private Double quantityOnHand;

    @Column(nullable = false)
    private Double quantityReserved;
}

