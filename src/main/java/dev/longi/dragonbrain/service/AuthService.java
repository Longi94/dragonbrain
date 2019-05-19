package dev.longi.dragonbrain.service;

import dev.longi.dragonbrain.controller.exception.NotFoundException;
import dev.longi.dragonbrain.controller.exception.UnauthorizedException;
import dev.longi.dragonbrain.entity.User;
import dev.longi.dragonbrain.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;

/**
 * @author lngtr
 * @since 2019-05-18
 */
@Service
public class AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean authenticate(String username, String password) {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new NotFoundException("User not found");
        }

        return this.passwordEncoder.matches(password, user.getHash());
    }

    public void checkRequest(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null) {
            throw new UnauthorizedException("Missing Authorization header");
        }

        boolean authorized;

        try {
            String token = authHeader.substring(5).trim();
            String[] data = new String(Base64.getDecoder().decode(token)).split(":");

            authorized = authenticate(data[0], data[1]);
        } catch (IndexOutOfBoundsException | IllegalArgumentException | NotFoundException e) {
            throw new UnauthorizedException("Invalid Authorization header");
        }

        if (!authorized) {
            throw new UnauthorizedException("Invalid credentials");
        }
    }
}
