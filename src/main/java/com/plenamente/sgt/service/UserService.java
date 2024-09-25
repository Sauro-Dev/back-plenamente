package com.plenamente.sgt.service;

import com.plenamente.sgt.domain.dto.UserDto.ListUser;
import com.plenamente.sgt.domain.dto.UserDto.RegisterUser;
import com.plenamente.sgt.infra.security.LoginRequest;
import com.plenamente.sgt.infra.security.TokenResponse;

import java.util.List;

public interface UserService {
    TokenResponse login(LoginRequest request);

    TokenResponse addUser(RegisterUser usuario);

    ListUser getCurrentUser();

    List<ListUser> getAllUsers();
}
