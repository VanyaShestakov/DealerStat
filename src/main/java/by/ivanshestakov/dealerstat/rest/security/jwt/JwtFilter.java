package by.ivanshestakov.dealerstat.rest.security.jwt;

import by.ivanshestakov.dealerstat.rest.security.userdetails.TheseUserDetails;
import by.ivanshestakov.dealerstat.rest.security.userdetails.TheseUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class JwtFilter extends GenericFilterBean {

    private final static String HEADER_KEY_NAME = "Authorization";
    private final static String BEARER_PREFIX = "Bearer ";

    private final JwtProvider jwtProvider;
    private final TheseUserDetailsService userDetailsService;

    @Autowired
    public JwtFilter(JwtProvider jwtProvider, TheseUserDetailsService userDetailsService) {
        this.jwtProvider = jwtProvider;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        String token = getToken((HttpServletRequest) request);
        if (token != null && jwtProvider.validateToken(token)) {
            String userLogin = jwtProvider.getLoginFromToken(token);
            TheseUserDetails customUserDetails = userDetailsService.loadUserByUsername(userLogin);
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        filterChain.doFilter(request, response);
    }

    private String getToken(HttpServletRequest request) {
        String value = request.getHeader(HEADER_KEY_NAME);
        if (value.startsWith(BEARER_PREFIX)) {
            return value.substring(7);
        }
        return null;
    }
}
