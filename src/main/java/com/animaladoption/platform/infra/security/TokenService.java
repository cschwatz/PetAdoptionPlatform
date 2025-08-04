package com.animaladoption.platform.infra.security;

import com.animaladoption.platform.domain.ong.Ong;
import com.animaladoption.platform.domain.person.Person;
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

    public String generateToken(Person person, Ong ong) {
        if (person == null && ong == null) {
            throw new IllegalArgumentException("Usuário informado é inválido");
        }
        try {
            String login = person == null ? ong.getLogin() : person.getLogin();
            String id = person == null ? ong.getId().toString() : person.getId().toString();
            String userType = person != null ? "PERSON" : "ONG"; // Add user type determination

            var alg = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("Pet Tracker")
                    .withSubject(login)
                    .withClaim("id", id) //guardar informações do usuario no token
                    .withClaim("userType", userType) // Add the userType claim
                    .withExpiresAt(expiracyDate())
                    .sign(alg);
        } catch (JWTCreationException exception){
            // Invalid Signing configuration / Couldn't convert Claims.
            throw new RuntimeException("Erro ao gerar token JWT", exception);
        }
    }

    public String getSubject(String tokenJWT) {
        try {
            var alg = Algorithm.HMAC256(secret);
            return JWT.require(alg)
                    .withIssuer("Pet Tracker")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTVerificationException exception){
            throw new RuntimeException("Token JWT invalido ou expirado");
        }
    }

    // Optional: Add method to extract userType from token
    public String getUserType(String tokenJWT) {
        try {
            var alg = Algorithm.HMAC256(secret);
            return JWT.require(alg)
                    .withIssuer("Pet Tracker")
                    .build()
                    .verify(tokenJWT)
                    .getClaim("userType")
                    .asString();
        } catch (JWTVerificationException exception){
            throw new RuntimeException("Token JWT invalido ou expirado");
        }
    }
    
    private Instant expiracyDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}