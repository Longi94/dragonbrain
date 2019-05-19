package dev.longi.dragonbrain.controller;

import dev.longi.dragonbrain.controller.dto.UserDto;
import dev.longi.dragonbrain.controller.exception.UnauthorizedException;
import dev.longi.dragonbrain.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author lngtr
 * @since 2019-05-18
 */
@Controller
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/auth")
    @ResponseStatus(value = HttpStatus.OK)
    public void auth(@ModelAttribute UserDto user) {
        if (!this.authService.authenticate(user.getUsername(), user.getPassword())) {
            throw new UnauthorizedException("Invalid credentials");
        }
    }
}
