package org.taylorsoft.taylorsoft.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.taylorsoft.taylorsoft.entity.enums.PhotoType;
import org.taylorsoft.taylorsoft.entity.enums.Ordre;

@Entity
@Table(name = "echantillon_photo")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EchantillonPhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "echantillon_id", nullable = false)
    private Echantillon echantillon;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PhotoType type;

    @Enumerated(EnumType.STRING)
    @Column(name = "photo_order", nullable = false)
    private Ordre order;

    @Column(nullable = false, length = 500)
    private String photoUrl;
}

