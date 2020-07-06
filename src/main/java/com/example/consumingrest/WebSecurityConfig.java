package com.example.consumingrest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private Boolean isSecure = true;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        if (isSecure) {
            http // http start
                    .authorizeRequests()
                    .antMatchers("/", "/home", "/actuator/health", "/actuator", "/actuator/error", "/example",
                            "/public/**", "/greeting/cors", "/favicon.ico")
                    .permitAll().anyRequest().authenticated().and() // authorize
                    .formLogin().loginPage("/login").permitAll().and() // login
                    .logout().permitAll(); // logout
        }

    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder().username("user").password("password").roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user);
    }
}