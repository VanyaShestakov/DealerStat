package by.ivanshestakov.dealerstat.rest.controller;

import by.ivanshestakov.dealerstat.rest.security.jwt.JwtProvider;
import by.ivanshestakov.dealerstat.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorizationController {

    private final JwtProvider jwtProvider;
    private final UserService userService;

    @Autowired
    public AuthorizationController(JwtProvider jwtProvider, UserService userService) {
        this.jwtProvider = jwtProvider;
        this.userService = userService;
    }


}
