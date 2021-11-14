package by.ivanshestakov.dealerstat.rest.service;

import by.ivanshestakov.dealerstat.rest.entity.User;

public interface UserService {

    public User findByEmail(String email);
}
