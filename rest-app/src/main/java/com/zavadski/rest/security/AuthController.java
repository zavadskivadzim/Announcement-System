package com.zavadski.rest.security;

import com.zavadski.dao.exception.UnacceptableName;
import com.zavadski.model.User;
import com.zavadski.model.security.AuthRequest;
import com.zavadski.model.security.AuthResponse;
import com.zavadski.model.security.RegistrationRequest;
import com.zavadski.service.api.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Objects;

@RestController
public class AuthController {

    private final Logger logger = LogManager.getLogger(AuthController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping("/register")
    @ResponseStatus(value = HttpStatus.CREATED)
    public String registerUser(@RequestBody @Valid RegistrationRequest registrationRequest, BindingResult result) {

        logger.info("Attempt to register a user with a login={}", registrationRequest.getLogin());

        if (result.hasErrors()) {
            logger.info("Entered data isn't correct: " + Objects.requireNonNull(result.getFieldError()).getDefaultMessage());
            throw new UnacceptableName(Objects.requireNonNull(result.getFieldError()).getDefaultMessage());
        } else {
            User user = new User();
            user.setPassword(registrationRequest.getPassword());
            user.setLogin(registrationRequest.getLogin());
            if (!this.userService.checkLoginOnUnique(user.getLogin())) {

                logger.warn("User with login " + user.getLogin() + " already exists.");

                throw new UnacceptableName("User with login " + user.getLogin() + " already exists.");
            } else {
                userService.register(user);

                logger.info("Registration of the user with login={} was successful" + user.getLogin());

                return "User with login " + user.getLogin() + " successfully registered";
            }
        }
    }

    @PostMapping("/auth")
    public AuthResponse auth(@RequestBody @Valid AuthRequest request, BindingResult result) {

        logger.info("Attempt to authorize a user with a login={}", request.getLogin());

        if (result.hasErrors()) {
            logger.info("Entered data isn't correct: " + Objects.requireNonNull(result.getFieldError()).getDefaultMessage());
            throw new UnacceptableName(Objects.requireNonNull(result.getFieldError()).getDefaultMessage());
        } else {
            User user = userService.findUserByLoginAndPassword(request.getLogin(), request.getPassword());
            String token = jwtProvider.generateToken(user.getLogin());

            logger.info("Authorization of the user with login={} was successful", request.getLogin());

            return new AuthResponse(token);
        }
    }
}
