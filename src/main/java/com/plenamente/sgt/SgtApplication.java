package com.plenamente.sgt;

import com.plenamente.sgt.domain.entity.Admin;
import com.plenamente.sgt.domain.entity.Rol;
import com.plenamente.sgt.infra.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@RequiredArgsConstructor
public class SgtApplication {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(SgtApplication.class, args);
	}

	@Bean
	public CommandLineRunner initDatabase() {
		return args -> {
			if (userRepository.findByUsername("admin").isEmpty()) {
				// Crear un Admin por defecto
				Admin defaultAdmin = new Admin();
				defaultAdmin.setName("Lozano");
				defaultAdmin.setPaternalSurname("Admin");
				defaultAdmin.setMaternalSurname("");
				defaultAdmin.setDni("00000000");
				defaultAdmin.setPhone("000000000");
				defaultAdmin.setPhoneBackup("000000000");
				defaultAdmin.setAddress("Admin Address");
				defaultAdmin.setPhoto(null); // O algún valor por defecto
				defaultAdmin.setEmail("admin@example.com");
				defaultAdmin.setUsername("admin");
				defaultAdmin.setPassword(passwordEncoder.encode("admin123"));
				defaultAdmin.setRol(Rol.ADMIN);
				defaultAdmin.setEnabled(true);

				userRepository.save(defaultAdmin);
			}
		};
	}
}
