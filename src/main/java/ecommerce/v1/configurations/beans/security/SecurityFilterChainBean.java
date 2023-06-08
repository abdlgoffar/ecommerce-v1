package ecommerce.v1.configurations.beans.security;

import ecommerce.v1.configurations.components.jwt.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityFilterChainBean {
    //BASIC AUTHENTICATION EXAMPLE
    //this bean is used to secure http header
//    @Bean
//    public org.springframework.security.web.SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        //secure request method
//        httpSecurity.csrf().disable()
//                .authorizeHttpRequests().requestMatchers("/users/register").permitAll()//this request not secure
//                .anyRequest().fullyAuthenticated()//secure all request except "users/register"
//                .and().httpBasic();//secure type
//        return httpSecurity.build();
//    }

    //JWT AUTHENTICATION EXAMPLE
    private AuthenticationProvider authenticationProvider;
    private JwtFilter jwtFilter;
    @Autowired
    public SecurityFilterChainBean(AuthenticationProvider authenticationProvider, JwtFilter jwtFilter) {
        this.authenticationProvider = authenticationProvider;
        this.jwtFilter = jwtFilter;
    }

    //bean for configuration http security, for example http cors, http csrf, http request, http filter, and http session.
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors()
                .and()
                .csrf().disable()
                .authorizeHttpRequests().requestMatchers("/users/**").permitAll()
                .and()
                .authorizeHttpRequests().requestMatchers("/products/**", "/categories/**", "/suppliers/**").authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(this.authenticationProvider).addFilterBefore(this.jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

}
