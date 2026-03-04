package org.taylorsoft.taylorsoft.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "stocks")
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

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "coutourier_id", nullable = false)
    private Coutourier coutourier; // le stock appartient au couturier

    @Column(nullable = false)
    @Builder.Default
    private Double quantiteOnHand = 0.0; // stock actuel en mètres

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "stock", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<StockMouvement> mouvements = new ArrayList<>();
}

