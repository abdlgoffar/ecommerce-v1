package ecommerce.v1.configurations.components.jwt;

import ecommerce.v1.services.UsersService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {
    private JwtUtil jwtUtil;
    private UsersService usersService;
    @Autowired
    public void setJwtUtil(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }
    @Autowired
    public void setUsersService(UsersService usersService) {
        this.usersService = usersService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");
        String payloadGenerated = null;
        String username = null;
        if (authorization != null && authorization.startsWith("Bearer ")) {
            payloadGenerated = authorization.substring(7);//take value authorization bearer token
            username = this.jwtUtil.extractUsername(payloadGenerated);//change value authorization bearer token to jwt token
        }
        //check token and already authorization
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails payloadAvailable = this.usersService.loadUserByUsername(username);//get token authorization
            if (this.jwtUtil.validateToken(payloadGenerated, payloadAvailable))/*check token valid*/{
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(payloadAvailable, null, payloadAvailable.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }
}
