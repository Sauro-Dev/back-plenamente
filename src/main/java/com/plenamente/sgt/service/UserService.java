package com.plenamente.sgt.service;

import com.plenamente.sgt.domain.dto.UserDto.RegisterUser;
import com.plenamente.sgt.infra.security.LoginRequest;
import com.plenamente.sgt.infra.security.TokenResponse;

public interface UserService {
    TokenResponse login(LoginRequest request);

    TokenResponse addUser(RegisterUser usuario);
}
