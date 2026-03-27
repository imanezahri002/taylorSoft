package org.taylorsoft.taylorsoft.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "fournisseurs")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Fournisseur extends User {

    private String nomEntreprise;

    private String registreCommerce;

    private String ice; // identifiant entreprise Maroc

    private String siteWeb;

    private String description;
}
