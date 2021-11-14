package by.ivanshestakov.dealerstat.rest.service.impl;

import by.ivanshestakov.dealerstat.rest.entity.User;
import by.ivanshestakov.dealerstat.rest.exception.RecordNotFoundException;
import by.ivanshestakov.dealerstat.rest.repository.UserRepository;
import by.ivanshestakov.dealerstat.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> {
                    throw new RecordNotFoundException("User with email=" + email + " not found");
                });
    }
}
