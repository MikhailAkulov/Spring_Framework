package ru.gb.myspringdemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import java.net.http.HttpRequest;

@Configuration
public class SecurityConfiguration {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests(registry -> registry
                        .requestMatchers("/ui/issues/**").hasAuthority("admin")
                        .requestMatchers("/ui/readers/**").hasAnyAuthority("reader", "admin")
                        .requestMatchers("/ui/books/**").authenticated()
                        .requestMatchers("/ui/**").permitAll()
                        .anyRequest().permitAll()
                )
                .formLogin(Customizer.withDefaults())
                .build();
    }
}
