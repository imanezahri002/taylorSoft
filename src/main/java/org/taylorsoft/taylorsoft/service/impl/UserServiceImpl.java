package org.taylorsoft.taylorsoft.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.taylorsoft.taylorsoft.dtos.request.UserRequest;
import org.taylorsoft.taylorsoft.dtos.response.UserResponse;
import org.taylorsoft.taylorsoft.entity.User;
import org.taylorsoft.taylorsoft.exception.DuplicateResourceException;
import org.taylorsoft.taylorsoft.exception.ResourceNotFoundException;
import org.taylorsoft.taylorsoft.mapper.UserMapper;
import org.taylorsoft.taylorsoft.repository.UserRepository;
import org.taylorsoft.taylorsoft.service.UserService;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponse save(UserRequest request) {
        // Si id est présent, c'est une mise à jour
        if (request.getId() != null) {
            User user = findByIdOrThrow(request.getId());

            // Vérifier l'unicité de l'email si changé
            if (!user.getEmail().equalsIgnoreCase(request.getEmail()) &&
                userRepository.existsByEmail(request.getEmail())) {
                throw new DuplicateResourceException("User", "email", request.getEmail());
            }

            userMapper.updateUserFromRequest(request, user);

            // Mettre à jour le mot de passe si fourni
            if (request.getPassword() != null && !request.getPassword().isBlank()) {
                user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
            }

            return userMapper.userToResponse(userRepository.save(user));
        }
        // Sinon, c'est une création
        else {
            if (userRepository.existsByEmail(request.getEmail())) {
                throw new DuplicateResourceException("User", "email", request.getEmail());
            }

            User user = userMapper.instantiateByRole(request.getRole());
            user.setEmail(request.getEmail());
            user.setRole(request.getRole());
            user.setNom(request.getNom());
            user.setPrenom(request.getPrenom());
            user.setTelephone(request.getTelephone());
            user.setAdresse(request.getAdresse());
            user.setStatut(request.getStatut() == null ? "ACTIF" : request.getStatut());
            user.setPasswordHash(passwordEncoder.encode(request.getPassword()));

            return userMapper.userToResponse(userRepository.save(user));
        }
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse getById(Long id) {
        return userMapper.userToResponse(findByIdOrThrow(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UserResponse> list(Pageable pageable) {
        return userRepository.findAll(pageable).map(userMapper::userToResponse);
    }


    @Override
    public void delete(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User", "id", id);
        }
        userRepository.deleteById(id);
    }

    private User findByIdOrThrow(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
    }
}

