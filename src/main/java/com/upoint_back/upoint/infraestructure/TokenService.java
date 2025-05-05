package com.upoint_back.upoint.infraestructure;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    /**
     * Gera um token JWT para o usuário.
     *
     * @param user O usuário para o qual o token será gerado.
     * @return O token gerado.
     */
    public String generateToken(com.upoint_back.upoint.domain.user.User user) {
        try {
            // Algoritmo de assinatura do token
            Algorithm algorithm = Algorithm.HMAC256(secret);

            // Criação do token
            return JWT.create()
                    .withIssuer("auth-api")
                    .withSubject(user.getLogin())  // Login do usuário no 'sub'
                    .withExpiresAt(genExpirationDate()) // Data de expiração
                    .sign(algorithm);  // Assina o token com o algoritmo
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar o token", exception);  // Em caso de falha na criação do token
        }
    }

    /**
     * Valida o token JWT e retorna o login (subject) extraído do token.
     *
     * @param token O token JWT a ser validado.
     * @return O login do usuário extraído do token, ou uma string vazia se o token for inválido.
     */
    public String validateToken(String token) {
        try {
            // Algoritmo de validação do token
            Algorithm algorithm = Algorithm.HMAC256(secret);

            // Valida o token e extrai o subject (login do usuário)
            return JWT.require(algorithm)
                    .withIssuer("auth-api") // Valida o issuer
                    .build()
                    .verify(token) // Verifica o token
                    .getSubject(); // Retorna o login do usuário (subject)
        } catch (JWTVerificationException exception) {
            // Se o token for inválido, retorna uma string vazia
            return "";
        }
    }

    /**
     * Gera a data de expiração do token (2 horas a partir do momento atual).
     *
     * @return A data de expiração do token.
     */
    private Instant genExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}