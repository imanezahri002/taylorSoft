package org.taylorsoft.taylorsoft.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "mesures")
    public class Mesure {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column
        private Double tourCou;
        private Double poitrine;
        private Double taille;
        private Double manche;
        private Double longueurDos;

        // Nouvelle mesure
        private Double jambe;
        private Double epaule;
        private Double bras;

        @OneToOne
        @JoinColumn(name = "client_id", referencedColumnName = "id")
        private Client client;

}
