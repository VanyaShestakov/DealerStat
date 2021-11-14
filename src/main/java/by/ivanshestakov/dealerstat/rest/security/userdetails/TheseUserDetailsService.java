package by.ivanshestakov.dealerstat.rest.security.userdetails;

import by.ivanshestakov.dealerstat.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class TheseUserDetailsService implements UserDetailsService {

    private final TheseUserDetails userDetails;
    private final UserService userService;

    @Autowired
    public TheseUserDetailsService(TheseUserDetails userDetails, UserService userService) {
        this.userDetails = userDetails;
        this.userService = userService;
    }

    @Override
    public TheseUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userDetails.toTheseUserDetails(userService.findByEmail(email));
    }
}
