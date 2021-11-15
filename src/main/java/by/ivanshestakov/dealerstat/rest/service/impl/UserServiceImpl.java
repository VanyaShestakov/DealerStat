package by.ivanshestakov.dealerstat.rest.service.impl;

import by.ivanshestakov.dealerstat.rest.entity.ConfirmationCode;
import by.ivanshestakov.dealerstat.rest.entity.User;
import by.ivanshestakov.dealerstat.rest.exception.IncorrectEmailException;
import by.ivanshestakov.dealerstat.rest.exception.IncorrectPasswordException;
import by.ivanshestakov.dealerstat.rest.exception.RecordNotFoundException;
import by.ivanshestakov.dealerstat.rest.repository.ConfirmationCodeRepository;
import by.ivanshestakov.dealerstat.rest.repository.UserRepository;
import by.ivanshestakov.dealerstat.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ConfirmationCodeRepository confirmationCodeRepository;
    private final PasswordEncoder encoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder encoder,
                           ConfirmationCodeRepository confirmationCodeRepository) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.confirmationCodeRepository = confirmationCodeRepository;
    }

    @Transactional
    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> {
                    throw new RecordNotFoundException("User with email=" + email + " not found");
                });
    }

    @Transactional
    @Override
    public User saveUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        user.setCreatedAt(new Date());
        userRepository.save(user);
        return user;
    }

    @Transactional
    @Override
    public User find(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> {
                    throw new IncorrectEmailException("User with this email not found");
                });
        if (encoder.matches(password, user.getPassword())) {
            return user;
        } else {
            throw new IncorrectPasswordException("Wrong password was entered");
        }
    }

    @Transactional
    @Override
    public void saveConfirmCodeForUser(String code, User user) {
        ConfirmationCode confirmCode = new ConfirmationCode();
        confirmCode.setCode(code);
        confirmCode.setUser(user);
        confirmCode.setExpirationTime(new Date());
        confirmationCodeRepository.save(confirmCode);
    }

}
