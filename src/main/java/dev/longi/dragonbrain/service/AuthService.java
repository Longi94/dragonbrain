package dev.longi.dragonbrain.service;

import dev.longi.dragonbrain.controller.exception.NotFoundException;
import dev.longi.dragonbrain.entity.User;
import dev.longi.dragonbrain.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
            throw new NotFoundException();
        }

        return this.passwordEncoder.matches(password, user.getHash());
    }
}
