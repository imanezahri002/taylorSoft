package org.taylorsoft.taylorsoft.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.taylorsoft.taylorsoft.dtos.request.UserRequest;
import org.taylorsoft.taylorsoft.dtos.response.UserResponse;

/**
 * Service pour la gestion des utilisateurs
 */
public interface UserService {

    /**
     * Créer ou mettre à jour un utilisateur
     */
    UserResponse save(UserRequest request);

    /**
     * Récupérer un utilisateur par son identifiant
     */
    UserResponse getById(Long id);

    /**
     * Lister les utilisateurs avec pagination
     */
    Page<UserResponse> list(Pageable pageable);

    /**
     * Supprimer un utilisateur par son identifiant
     */
    void delete(Long id);
}
