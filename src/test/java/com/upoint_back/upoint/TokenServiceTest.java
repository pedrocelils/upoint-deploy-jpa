package com.upoint_back.upoint;

import com.upoint_back.upoint.infraestructure.TokenService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TokenServiceTest {

    private static final String SECRET = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJhdXRoLWFwaSIsInN1YiI6ImNhcmxvcyIsImV4cCI6MTc0NTc4MTY3Nn0.eZx6wC-tz4QOyEiFXKwy3yFfH6q5OYnbbrQCz1EKA3Y";  // Use um segredo para testes

    private TokenService tokenService = new TokenService();

    @Test
    public void testGenerateAndValidateToken() {
        // Simulação de um usuário com login "admin"
        com.upoint_back.upoint.domain.user.User user = new com.upoint_back.upoint.domain.user.User();
        user.setLogin("admin");

        // Gera o token para o usuário
        String token = tokenService.generateToken(user);
        System.out.println("Token gerado: " + token);

        // Valida o token gerado
        String login = tokenService.validateToken(token);
        assertEquals("admin", login);
        System.out.println("Login validado a partir do token: " + login);
    }
}
