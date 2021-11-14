package by.ivanshestakov.dealerstat.rest.service.impl;

import by.ivanshestakov.dealerstat.rest.entity.User;
import by.ivanshestakov.dealerstat.rest.exception.RecordNotFoundException;
import by.ivanshestakov.dealerstat.rest.repository.UserRepository;
import by.ivanshestakov.dealerstat.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
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
        userRepository.save(user);
        return user;
    }

    @Override
    public User find(String email, String password) {
        return null;
    }


}
