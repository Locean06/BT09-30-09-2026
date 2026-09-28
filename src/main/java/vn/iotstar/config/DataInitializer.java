package vn.iotstar.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import vn.iotstar.entity.Role;
import vn.iotstar.entity.User;
import vn.iotstar.repository.RoleRepository;
import vn.iotstar.repository.UserRepository;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initData(
            RoleRepository roleRepository,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {

        return args -> {

            Role userRole = roleRepository
                    .findByNameIgnoreCase("USER")
                    .orElseGet(() ->
                            roleRepository.save(new Role("USER")));

            if (!userRepository.existsByEmailIgnoreCase("user@gmail.com")) {

                User user = new User();

                user.setEmail("user@gmail.com");
                user.setFullName("Nguyen Van A");

                user.setPassword(
                        passwordEncoder.encode("123456")
                );

                user.setEnabled(true);
                user.setRole(userRole);

                userRepository.save(user);
            }
        };
    }
}