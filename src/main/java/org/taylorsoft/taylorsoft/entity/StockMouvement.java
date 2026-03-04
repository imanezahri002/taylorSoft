package org.taylorsoft.taylorsoft.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.taylorsoft.taylorsoft.entity.enums.MovementType;

import java.time.LocalDateTime;

@Entity
@Table(name = "stock_mouvements")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockMouvement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "stock_id", nullable = false)
    private Stock stock;

    @Column(nullable = false)
    private Double quantite; // quantité en mètres

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MovementType type; // IN, OUT, AJUSTEMENT

    @Column(nullable = false)
    private LocalDateTime dateMouvement;

    @Column(length = 500)
    private String description; // ex: "Commande #123 livrée"

    @PrePersist
    protected void onCreate() {
        if (dateMouvement == null) {
            dateMouvement = LocalDateTime.now();
        }
    }
}

