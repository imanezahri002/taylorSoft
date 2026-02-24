package org.taylorsoft.taylorsoft.service;

import org.taylorsoft.taylorsoft.dtos.request.LoginRequest;
import org.taylorsoft.taylorsoft.dtos.request.RegisterRequest;
import org.taylorsoft.taylorsoft.dtos.response.AuthResponse;

public interface AuthService {

    AuthResponse login(LoginRequest loginRequest);

    AuthResponse register(RegisterRequest registerRequest);
}

