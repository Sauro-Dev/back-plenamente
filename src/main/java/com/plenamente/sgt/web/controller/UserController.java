package com.plenamente.sgt.web.controller;

import com.plenamente.sgt.domain.dto.UserDto.ListUser;
import com.plenamente.sgt.domain.dto.UserDto.RegisterUser;
import com.plenamente.sgt.infra.security.LoginRequest;
import com.plenamente.sgt.infra.security.TokenResponse;
import com.plenamente.sgt.service.UserService;
import com.plenamente.sgt.service.auth.AuthorizationService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin("*")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AuthorizationService authorizationService;

    @PostMapping("/login")
    @Transactional
    public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(userService.login(request));
    }

    @PostMapping("/register")
    @Transactional
    public ResponseEntity<TokenResponse> addUser(@RequestBody @Valid RegisterUser data) {
        authorizationService.authorizeRegisterUser();
        return ResponseEntity.ok(userService.addUser(data));
    }

    @GetMapping("/select/{id}")
    public ResponseEntity<ListUser> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ListUser>> getAllUsers() {
        authorizationService.authorizeRegisterUser();
        return ResponseEntity.ok(userService.getAllUsers());
    }
}
