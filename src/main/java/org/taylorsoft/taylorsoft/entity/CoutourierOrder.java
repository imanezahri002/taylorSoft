package org.taylorsoft.taylorsoft.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.taylorsoft.taylorsoft.entity.enums.CoutourierOrderStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "coutourier_orders")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoutourierOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CoutourierOrderStatus status;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @Column(nullable = false)
    private Double prixTotal;

    // Foreign key vers Coutourier (celui qui passe la commande)
    @ManyToOne
    @JoinColumn(name = "coutourier_id", nullable = false)
    private Coutourier coutourier;

    // Foreign key vers Fournisseur (celui qui reçoit la commande)
    @ManyToOne
    @JoinColumn(name = "fournisseur_id", nullable = false)
    private Fournisseur fournisseur;

    // Liste des items de la commande
    @OneToMany(mappedBy = "coutourierOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<CoutourierOrderItem> items = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

}

