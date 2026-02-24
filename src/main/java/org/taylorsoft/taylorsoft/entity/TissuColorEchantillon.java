package org.taylorsoft.taylorsoft.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tissu_color_echantillon")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor

public class TissuColorEchantillon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tissu_color_id", nullable = false)
    private TissuColor tissuColor;

    @ManyToOne
    @JoinColumn(name = "echantillon_id", nullable = false)
    private Echantillon echantillon;





}
