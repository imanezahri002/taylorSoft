package org.taylorsoft.taylorsoft.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.taylorsoft.taylorsoft.entity.enums.Ordre;

@Entity
@Table(name = "model_photo")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModelPhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Foreign key to ModelColor
    @ManyToOne
    @JoinColumn(name = "model_color_id", nullable = false)
    private ModelColor modelColor;

    @Column(nullable = false)
    private Boolean principal;

    @Column(nullable = false, length = 500)
    private String photoUrl;

    @Enumerated(EnumType.STRING)
    @Column(name = "photo_order", nullable = false)
    private Ordre order;
}

