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
                        // Auth
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                        .requestMatchers(HttpMethod.GET, "/auth/funcionarios/count").permitAll()

                        // Empresa
                        .requestMatchers(HttpMethod.POST, "/empresa").permitAll()
                        .requestMatchers(HttpMethod.GET, "/empresa").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/empresa").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/empresa").permitAll()

                        // Endereço
                        .requestMatchers(HttpMethod.POST, "/endereco").permitAll()
                        .requestMatchers(HttpMethod.GET, "/endereco").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/endereco").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/endereco").permitAll()

                        // Registros
                        .requestMatchers(HttpMethod.POST, "/registros").permitAll()
                        .requestMatchers(HttpMethod.GET, "/registros").permitAll()
                        .requestMatchers(HttpMethod.GET, "/registros/{id}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/registros/historico").permitAll()
                        .requestMatchers(HttpMethod.GET, "/registros/count").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/registros").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/registros").permitAll()

                        // Funcionário (User)
                        .requestMatchers(HttpMethod.POST, "/cliente").permitAll() // Você pode renomear "/cliente" para "/funcionarios" se desejar padronizar

                        // Qualquer outra requisição exige autenticação
                        .anyRequest().authenticated()
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
        config.setAllowedOrigins(List.of("https://www.upoint.com.br"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
