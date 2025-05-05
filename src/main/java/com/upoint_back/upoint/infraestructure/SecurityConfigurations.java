package com.upoint_back.upoint.infraestructure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    private final CorsConfigurationSource corsConfigurationSource;

    public SecurityConfigurations(CorsConfigurationSource corsConfigurationSource) {
        this.corsConfigurationSource = corsConfigurationSource;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf().disable()
                .cors().configurationSource(corsConfigurationSource()) // Configuração de CORS
                .and()
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()  // Permite POST em /auth/login
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()  // Permite POST em /auth/register
                        .requestMatchers(HttpMethod.POST, "/empresa").permitAll()  // Permite POST em /empresa
                        .requestMatchers(HttpMethod.POST, "/endereco").permitAll()  // Permite POST em /endereco
                        .requestMatchers(HttpMethod.POST, "/registros").permitAll()  // Permite POST em /registros
                        .requestMatchers(HttpMethod.GET, "/registros").permitAll()
                        .requestMatchers(HttpMethod.GET, "/registros/{id}").permitAll()// Permite GET em /registros
                        .requestMatchers(HttpMethod.GET, "/empresa").permitAll()  // Permite GET em /empresa
                        .requestMatchers(HttpMethod.GET, "/endereco").permitAll()  // Permite GET em /endereco
                        .requestMatchers(HttpMethod.PUT, "/registros").permitAll()  // Permite PUT em /registros
                        .requestMatchers(HttpMethod.PUT, "/empresa").permitAll()  // Permite PUT em /empresa
                        .requestMatchers(HttpMethod.PUT, "/endereco").permitAll()  // Permite PUT em /endereco
                        .requestMatchers(HttpMethod.DELETE, "/registros").permitAll()  // Permite DELETE em /registros
                        .requestMatchers(HttpMethod.DELETE, "/empresa").permitAll()  // Permite DELETE em /empresa
                        .requestMatchers(HttpMethod.DELETE, "/endereco").permitAll()
                        .requestMatchers(HttpMethod.POST, "/cliente").permitAll()
                        .requestMatchers(HttpMethod.GET, "/registros/historico").permitAll()
                        .requestMatchers(HttpMethod.GET, "/auth/funcionarios/count").permitAll()
                        .requestMatchers(HttpMethod.GET, "/registros/count").permitAll()
                        .anyRequest().authenticated()  // Exige autenticação
                )
                .build();
    }

    private CorsConfigurationSource corsConfigurationSource() {
        return null;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("http://localhost:8081", "http://localhost:3000"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
