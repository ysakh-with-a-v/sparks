package com.sparks.interview.app.configurations;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;


@Configuration
@EnableWebSecurity
public class SecurityConfig  {

    private static final String[]  swaggerWhitelist={
        "/swagger-ui/**",
        "/v3/api-docs/**",
            "/swagger-resources/**",
            "/swagger-resources"

    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(auth -> auth.
                requestMatchers("/").permitAll()
                .requestMatchers("/api/revenue/**").permitAll()

                .requestMatchers(swaggerWhitelist).permitAll()

                .requestMatchers("/api/pdf/**").permitAll()

                .requestMatchers(HttpMethod.GET,"/api/sale/**").permitAll()
                .requestMatchers(HttpMethod.POST,"/api/sale/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE,"/api/sale/**").hasRole("ADMIN")

                .requestMatchers(HttpMethod.GET,"/api/product/**").permitAll()
                .requestMatchers(HttpMethod.POST,"/api/product/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT,"/api/product/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE,"/api/product/**").hasRole("ADMIN")

                .anyRequest().authenticated()).httpBasic(httpSecurityHttpBasicConfigurer -> {})
                .csrf(csrf -> csrf.disable());

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("admin").password("{noop}adminpass").roles("ADMIN").build());
        manager.createUser(User.withUsername("regular").password("{noop}regularpass").roles("REGULAR").build());
        manager.createUser(User.withUsername("public").password("{noop}publicpass").roles("PUBLIC").build());
        return manager;
    }


}