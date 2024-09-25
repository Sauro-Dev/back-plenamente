package com.plenamente.sgt.service.impl;

import com.plenamente.sgt.domain.dto.UserDto.RegisterUser;
import com.plenamente.sgt.domain.entity.AdminTherapist;
import com.plenamente.sgt.domain.entity.Secretary;
import com.plenamente.sgt.domain.entity.Therapist;
import com.plenamente.sgt.domain.entity.User;
import com.plenamente.sgt.infra.repository.UserRepository;
import com.plenamente.sgt.infra.security.JwtService;
import com.plenamente.sgt.infra.security.LoginRequest;
import com.plenamente.sgt.infra.security.TokenResponse;
import com.plenamente.sgt.service.UserFactory;
import com.plenamente.sgt.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public TokenResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con username: " + request.getUsername()));

        if (!user.isEnabled()) {
            throw new DisabledException("Este usuario ha sido deshabilitado.");
        }

        String token = jwtService.getToken(user, user);
        return TokenResponse.builder()
                .token(token)
                .build();
    }

    @Override
    public TokenResponse addUser(RegisterUser data) {
        boolean isAlsoTherapist = data.paymentSession() != null;
        User user = UserFactory.createUser(data.role(), isAlsoTherapist);

        // atributos comunes a todos los usuarios
        user.setName(data.name());
        user.setPaternalSurname(data.paternalSurname());
        user.setMaternalSurname(data.maternalSurname());
        user.setDni(data.dni());
        user.setPhone(data.phone());
        user.setPhoneBackup(data.phoneBackup());
        user.setAddress(data.address());
        user.setBirthdate(data.birthdate());
        user.setEmail(data.email());
        user.setUsername(data.username());
        user.setPassword(passwordEncoder.encode(data.password()));
        user.setRol(data.role());

        // atributos específicos de cada tipo de usuario
        if (user instanceof Therapist) {
            Double paymentSession = data.paymentSession();
            if (paymentSession != null) {
                ((Therapist) user).setPaymentSession(paymentSession);
            }
        } else if (user instanceof Secretary) {
            Double paymentMonthly = data.paymentMonthly();
            if (paymentMonthly != null) {
                ((Secretary) user).setPaymentMonthly(paymentMonthly);
            }
        } else if (user instanceof AdminTherapist) {
            Double paymentSession = data.paymentSession();
            if (paymentSession != null) {
                ((AdminTherapist) user).setPaymentSession(paymentSession);
            }
        }



        userRepository.save(user);

        String token = jwtService.getToken(user, user);
        return TokenResponse.builder()
                .token(token)
                .build();
    }

}