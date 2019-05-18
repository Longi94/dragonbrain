package dev.longi.dragonbrain.service;

import dev.longi.dragonbrain.controller.dto.UserDto;
import dev.longi.dragonbrain.controller.exception.UnauthorizedException;
import dev.longi.dragonbrain.entity.User;
import dev.longi.dragonbrain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;

/**
 * @author lngtr
 * @since 2017-12-27
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User checkPrincipal(Principal principal) {
        if (principal == null) {
            throw new UnauthorizedException();
        }

        User user = userRepository.findByUsername(principal.getName());

        if (user == null) {
            throw new UnauthorizedException();
        }

        return user;
    }


    public User registerUser(UserDto userDto, Principal principal) {
        if (usernameExists(userDto.getUsername())) {
            return null;
        }

        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setHash(passwordEncoder.encode(userDto.getPassword()));

        return userRepository.save(user);
    }

    public boolean usernameExists(String username) {
        User user = userRepository.findByUsername(username);
        return user != null;
    }
}
