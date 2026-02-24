package org.taylorsoft.taylorsoft.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Entité représentant un client dans l'application TaylorSoft
 * Hérite de User
 */
@Entity
@Table(name = "clients")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Client extends User {

    @Column(name = "date_naissance")
    private LocalDate dateNaissance;

    @Column(length = 1000)
    private String notes;

    @OneToOne(mappedBy = "client", cascade = CascadeType.ALL)
    private Mesure mesure;
}

