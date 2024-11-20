package io.github.generallyspecific.journalv2.oauth2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/", "/login/oauth2/**", "/login/**", "/logout/**").permitAll();
                    auth.anyRequest().authenticated();
                })
                .logout(logout -> {
                    logout.logoutSuccessUrl("/"); // redirect to home page after logout
                    logout.clearAuthentication(true);
                    logout.invalidateHttpSession(true);
                })
                .oauth2Login(Customizer.withDefaults()) // use default oauth2 login page
                .build();
    }
}