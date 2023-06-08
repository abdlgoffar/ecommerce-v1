package ecommerce.v1.configurations.beans.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

@Configuration
public class AuthenticationManagerBean {
    //BASIC AUTHENTICATION EXAMPLE

//    private AuthenticationProvider authenticationProvider;
//    @Autowired
//    public AuthenticationManager(AuthenticationProvider authenticationProvider) {
//        this.authenticationProvider = authenticationProvider;
//    }
//    //bean for add authentication provider to http spring security
//    @Bean
//    public org.springframework.security.authentication.AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {
//        AuthenticationManagerBuilder authenticationManagerBuilder = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
//        authenticationManagerBuilder.authenticationProvider(this.authenticationProvider);
//        return authenticationManagerBuilder.build();
//    }

    //JWT AUTHENTICATION EXAMPLE

    //bean for add authentication data to http security
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
