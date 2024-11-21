package service;

import com.plenamente.sgt.domain.dto.UserDto.ListUser;
import com.plenamente.sgt.domain.dto.UserDto.RegisterUser;
import com.plenamente.sgt.domain.entity.*;
import com.plenamente.sgt.infra.repository.UserRepository;
import com.plenamente.sgt.infra.security.JwtService;
import com.plenamente.sgt.infra.security.LoginRequest;
import com.plenamente.sgt.infra.security.TokenResponse;
import com.plenamente.sgt.service.impl.UserServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private JwtService jwtService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void login_ShouldReturnToken_WhenCredentialsAreValid() {
        // Arrange
        String username = "testUser";
        String password = "testPassword";

        LoginRequest loginRequest = new LoginRequest(username, password);
        User user = new Admin();
        user.setUsername(username);
        user.setPassword(password);
        user.setEnabled(true);

        Authentication authentication = mock(Authentication.class);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(authentication);
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));
        when(jwtService.getToken(user, user)).thenReturn("mockedToken");

        // Act
        TokenResponse result = userService.login(loginRequest);

        // Assert
        assertNotNull(result);
        assertEquals("mockedToken", result.getToken());
        verify(authenticationManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(userRepository, times(1)).findByUsername(username);
        verify(jwtService, times(1)).getToken(user, user);
    }

    @Test
    void login_ShouldThrowException_WhenUserIsDisabled() {
        // Arrange
        String username = "testUser";
        String password = "testPassword";

        LoginRequest loginRequest = new LoginRequest(username, password);
        User user = new Admin();
        user.setUsername(username);
        user.setPassword(password);
        user.setEnabled(false);

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(mock(Authentication.class));
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));

        // Act & Assert
        assertThrows(DisabledException.class, () -> userService.login(loginRequest));
        verify(authenticationManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(userRepository, times(1)).findByUsername(username);
    }

    @Test
    void addUser() {
        // Arrange
        RegisterUser registerUser = new RegisterUser(
                "Test Name",
                "Paternal LastName",
                "Maternal LastName",
                "123456789",
                "987654321",
                "12345678",
                "test@example.com",
                "Test Address",
                LocalDate.of(1990, 1, 1),
                "testUser",
                "testPassword",
                Rol.ADMIN,
                null,
                null
        );

        Admin user = new Admin();
        user.setUsername(registerUser.username());
        user.setPassword("encodedPassword");

        when(passwordEncoder.encode(registerUser.password())).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(jwtService.getToken(any(User.class), any(User.class))).thenReturn("mockedToken");

        // Act
        TokenResponse result = userService.addUser(registerUser);

        // Assert
        assertNotNull(result);
        assertEquals("mockedToken", result.getToken());
        verify(passwordEncoder, times(1)).encode(registerUser.password());
        verify(userRepository, times(1)).save(any(User.class));
        verify(jwtService, times(1)).getToken(any(User.class), any(User.class));
    }

    @Test
    void getAllUsers() {
        // Arrange
        List<User> users = new ArrayList<>();
        Admin user = new Admin();
        user.setIdUser(1L);
        user.setUsername("testUser");
        user.setName("Test Name");
        user.setEmail("test@example.com");
        user.setRol(Rol.ADMIN);
        user.setPaternalSurname("LastName");
        user.setMaternalSurname("MotherLastName");
        user.setDni("12345678");
        user.setPhone("123456789");
        user.setPhoneBackup("987654321");
        user.setAddress("Test Address");
        users.add(user);

        when(userRepository.findAll()).thenReturn(users);

        // Act
        List<ListUser> result = userService.getAllUsers();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("testUser", result.get(0).username());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void getUserById() {
        // Arrange
        Long userId = 1L;
        Admin user = new Admin();
        user.setIdUser(userId);
        user.setUsername("testUser");
        user.setName("Test Name");
        user.setEmail("test@example.com");
        user.setRol(Rol.ADMIN);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // Act
        ListUser result = userService.getUserById(userId);

        // Assert
        assertNotNull(result);
        assertEquals(userId, result.id());
        verify(userRepository, times(1)).findById(userId);
    }
}
