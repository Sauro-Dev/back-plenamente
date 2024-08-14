package com.plenamente.sgt.web.controller;

import com.plenamente.sgt.domain.dto.UserDto.RegisterUser;
import com.plenamente.sgt.infra.security.LoginRequest;
import com.plenamente.sgt.infra.security.TokenResponse;
import com.plenamente.sgt.service.UserService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin("*")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    @Transactional
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        TokenResponse tokenResponse = userService.login(request);
        return ResponseEntity.ok(tokenResponse);
    }

    @PostMapping("/register")
    @Transactional
    public ResponseEntity<?> addUser(@RequestBody @Valid RegisterUser data) {
        return ResponseEntity.ok(userService.addUser(data));
    }



}
