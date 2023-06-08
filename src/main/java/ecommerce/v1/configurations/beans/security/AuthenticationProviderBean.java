package ecommerce.v1.configurations.beans.security;

import ecommerce.v1.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class AuthenticationProviderBean {
    private UsersService usersService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    public AuthenticationProviderBean(UsersService usersService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.usersService = usersService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    //bean for add authorization user data to authentication manager
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(this.bCryptPasswordEncoder);
        daoAuthenticationProvider.setUserDetailsService(this.usersService);
        return daoAuthenticationProvider;
    }
}
