package ru.pcs.crowdfunding.auth.security.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.pcs.crowdfunding.auth.repositories.RolesRepository;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

@Component
@Slf4j
public class JwtTokenProvider implements TokenProvider {

    @Value("${security.jwt_secret_key}")
    private String jwtSecretKey;

    @Autowired
    private RolesRepository rolesRepository;

    @Override
    public String generate(TokenContent tokenContent, Duration lifeTime) {
        return JWT.create()
                .withExpiresAt(calcExpirationDate(lifeTime))
                .withSubject(tokenContent.getUserId().toString())
                .withClaim("role", tokenContent.getRole().getName().toString())
                .sign(Algorithm.HMAC256(jwtSecretKey));
    }

    @Override
    public TokenContent decode(String token) throws Exception {
        try {
            DecodedJWT jwt = JWT.require(Algorithm.HMAC256(jwtSecretKey))
                    .build()
                    .verify(token);

            Long userId = Long.parseLong(jwt.getClaim("sub").asString());
            String role = jwt.getClaim("role").asString();

            return TokenContent.builder()
                    .userId(userId)
                    .role(rolesRepository.getRoleByName(role))
                    .build();
        } catch (Exception e) {
            log.error("Exception while decoding token", e);
            throw e;
        }
    }

    private static Date calcExpirationDate(Duration lifeTime) {
        Instant expirationInstant = Instant.now().plus(lifeTime);
        return Date.from(expirationInstant);
    }
}
