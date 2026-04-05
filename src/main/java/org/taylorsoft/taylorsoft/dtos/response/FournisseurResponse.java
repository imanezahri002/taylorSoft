package org.taylorsoft.taylorsoft.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.taylorsoft.taylorsoft.entity.enums.Role;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FournisseurResponse {

    // Champs hérités de User
    private Long id;
    private String email;
    private Role role;
    private String statut;
    private String nom;
    private String prenom;
    private String telephone;
    private String profilePicture;
    private String adresse;

    // Champs spécifiques à Fournisseur
    private String nomEntreprise;
    private String registreCommerce;
    private String ice;
    private String siteWeb;
    private String description;
}

