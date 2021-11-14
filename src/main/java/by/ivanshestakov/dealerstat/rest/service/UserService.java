package by.ivanshestakov.dealerstat.rest.service;

import by.ivanshestakov.dealerstat.rest.entity.User;

public interface UserService {

    public User findByEmail(String email);

    public User saveUser(User user);

    public User find(String email, String password);

}
