package by.ivanshestakov.dealerstat.rest.controller;

import by.ivanshestakov.dealerstat.rest.dto.UserDTO;
import by.ivanshestakov.dealerstat.rest.dto.converter.UserConverter;
import by.ivanshestakov.dealerstat.rest.entity.User;
import by.ivanshestakov.dealerstat.rest.security.entity.AuthRequest;
import by.ivanshestakov.dealerstat.rest.security.entity.AuthResponse;
import by.ivanshestakov.dealerstat.rest.security.jwt.JwtProvider;
import by.ivanshestakov.dealerstat.rest.service.EmailService;
import by.ivanshestakov.dealerstat.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Random;
import java.util.UUID;

@RestController
public class AuthenticationController {

    private final JwtProvider jwtProvider;
    private final UserService userService;
    private final UserConverter converter;
    private final EmailService emailService;

    @Autowired
    public AuthenticationController(JwtProvider jwtProvider,
                                    UserService userService,
                                    UserConverter converter,
                                    EmailService emailService) {
        this.jwtProvider = jwtProvider;
        this.userService = userService;
        this.converter = converter;
        this.emailService = emailService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO) {
        userService.saveUser(converter.convertToEntity(userDTO));
        String code = UUID.randomUUID().toString();
        String email = userDTO.getEmail();
        emailService.sendMessage(email, "Confirmation code", code);
        userService.saveConfirmCodeForUser(code, userService.findByEmail(userDTO.getEmail()));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/register/confirm/{code}")
    public ResponseEntity<?> confirmRegistration(@PathVariable String code) {
        return null;
    }

    @PostMapping("/authenticate")
    public AuthResponse auth(@RequestBody AuthRequest request) {
        User user = userService.find(request.getEmail(), request.getPassword());
        String token = jwtProvider.generateToken(user.getEmail());
        return new AuthResponse(token);
    }

}
