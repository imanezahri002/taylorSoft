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
import org.taylorsoft.taylorsoft.entity.User;
import org.taylorsoft.taylorsoft.exception.DuplicateResourceException;
import org.taylorsoft.taylorsoft.jwt.JwtService;
import org.taylorsoft.taylorsoft.mapper.AuthMapper;
import org.taylorsoft.taylorsoft.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements org.taylorsoft.taylorsoft.service.AuthService {

    private final UserRepository userRepository;
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

        // Mapper la requête vers l'entité User
        User user = authMapper.registerRequestToUser(registerRequest);

        // Encoder le mot de passe
        user.setPasswordHash(passwordEncoder.encode(registerRequest.getPassword()));

        // Sauvegarder l'utilisateur
        User savedUser = userRepository.save(user);

        // Générer le token JWT
        String token = jwtService.generateToken(savedUser);

        // Créer la réponse
        AuthResponse response = authMapper.userToAuthResponse(savedUser);
        response.setToken(token);

        return response;
    }
}

