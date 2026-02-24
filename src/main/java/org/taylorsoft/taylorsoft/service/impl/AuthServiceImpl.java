package org.taylorsoft.taylorsoft.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.taylorsoft.taylorsoft.dtos.request.LoginRequest;
import org.taylorsoft.taylorsoft.dtos.request.RegisterRequest;
import org.taylorsoft.taylorsoft.dtos.response.AuthResponse;
import org.taylorsoft.taylorsoft.entity.Client;
import org.taylorsoft.taylorsoft.entity.Coutourier;
import org.taylorsoft.taylorsoft.entity.Fournisseur;
import org.taylorsoft.taylorsoft.entity.User;
import org.taylorsoft.taylorsoft.exception.DuplicateResourceException;
import org.taylorsoft.taylorsoft.exception.InvalidOperationException;
import org.taylorsoft.taylorsoft.jwt.JwtService;
import org.taylorsoft.taylorsoft.mapper.AuthMapper;
import org.taylorsoft.taylorsoft.repository.ClientRepository;
import org.taylorsoft.taylorsoft.repository.CoutourierRepository;
import org.taylorsoft.taylorsoft.repository.FournisseurRepository;
import org.taylorsoft.taylorsoft.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements org.taylorsoft.taylorsoft.service.AuthService {

    private final UserRepository userRepository;
    private final ClientRepository clientRepository;
    private final CoutourierRepository coutourierRepository;
    private final FournisseurRepository fournisseurRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final AuthMapper authMapper;

    @Override
    public AuthResponse login(LoginRequest loginRequest) {
        // Authentifier l'utilisateur
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(),
                loginRequest.getPassword()
            )
        );

        // Récupérer l'utilisateur authentifié
        User user = (User) authentication.getPrincipal();

        // Générer le token JWT
        String token = jwtService.generateToken(user);

        // Créer la réponse
        AuthResponse response = authMapper.userToAuthResponse(user);
        response.setToken(token);

        return response;
    }

    @Override
    @Transactional
    public AuthResponse register(RegisterRequest registerRequest) {
        // Vérifier si l'email existe déjà
        if (userRepository.findByEmail(registerRequest.getEmail()).isPresent()) {
            throw new DuplicateResourceException("Un utilisateur avec cet email existe déjà");
        }

        // Créer l'utilisateur selon le rôle
        User savedUser;
        String encodedPassword = passwordEncoder.encode(registerRequest.getPassword());

        switch (registerRequest.getRole()) {
            case CLIENT:
                Client client = new Client();
                client.setEmail(registerRequest.getEmail());
                client.setPasswordHash(encodedPassword);
                client.setRole(registerRequest.getRole());
                client.setNom(registerRequest.getNom());
                client.setPrenom(registerRequest.getPrenom());
                client.setTelephone(registerRequest.getTelephone());
                client.setAdresse(registerRequest.getAdresse());
                client.setStatut("ACTIF");
                savedUser = clientRepository.save(client);
                break;

            case COUTOURIER:
                Coutourier coutourier = new Coutourier();
                coutourier.setEmail(registerRequest.getEmail());
                coutourier.setPasswordHash(encodedPassword);
                coutourier.setRole(registerRequest.getRole());
                coutourier.setNom(registerRequest.getNom());
                coutourier.setPrenom(registerRequest.getPrenom());
                coutourier.setTelephone(registerRequest.getTelephone());
                coutourier.setAdresse(registerRequest.getAdresse());
                coutourier.setStatut("ACTIF");
                savedUser = coutourierRepository.save(coutourier);
                break;

            case FOURNISSEUR:
                Fournisseur fournisseur = new Fournisseur();
                fournisseur.setEmail(registerRequest.getEmail());
                fournisseur.setPasswordHash(encodedPassword);
                fournisseur.setRole(registerRequest.getRole());
                fournisseur.setNom(registerRequest.getNom());
                fournisseur.setPrenom(registerRequest.getPrenom());
                fournisseur.setTelephone(registerRequest.getTelephone());
                fournisseur.setAdresse(registerRequest.getAdresse());
                fournisseur.setStatut("ACTIF");
                savedUser = fournisseurRepository.save(fournisseur);
                break;

            case ADMIN:
                User admin = new User();
                admin.setEmail(registerRequest.getEmail());
                admin.setPasswordHash(encodedPassword);
                admin.setRole(registerRequest.getRole());
                admin.setNom(registerRequest.getNom());
                admin.setPrenom(registerRequest.getPrenom());
                admin.setTelephone(registerRequest.getTelephone());
                admin.setAdresse(registerRequest.getAdresse());
                admin.setStatut("ACTIF");
                savedUser = userRepository.save(admin);
                break;

            default:
                throw new InvalidOperationException("Rôle non valide: " + registerRequest.getRole());
        }

        // Générer le token JWT
        String token = jwtService.generateToken(savedUser);

        // Créer la réponse
        AuthResponse response = authMapper.userToAuthResponse(savedUser);
        response.setToken(token);

        return response;
    }
}

