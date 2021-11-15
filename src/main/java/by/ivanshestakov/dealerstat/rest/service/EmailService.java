package by.ivanshestakov.dealerstat.rest.service;

public interface EmailService {
    void sendMessage(String email, String title, String message);
}
