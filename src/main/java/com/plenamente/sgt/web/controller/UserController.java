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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        TokenResponse tokenResponse = userService.login(request);
        return ResponseEntity.ok(tokenResponse);
    }

    @PostMapping("/register")
    @Transactional
    public ResponseEntity<?> addUser(@RequestBody @Valid RegisterUser data) {
        return ResponseEntity.ok(userService.addUser(data));
    }

    // New method to get the current logged-in user
    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(@AuthenticationPrincipal Authentication authentication) {
        return ResponseEntity.ok(userService.getCurrentUser(authentication.getName()));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllUsers() {
        authorizationService.authorizeRegisterUser();
        List<ListUser> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

}
