package org.example.auth;

import io.dropwizard.auth.Authenticator;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.example.models.JwtToken;
import org.example.models.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.SecretKey;
import java.util.Optional;

public class JwtAuthenticator implements Authenticator<String, JwtToken> {
    static final Logger LOGGER =
            LoggerFactory.getLogger(JwtAuthenticator.class);
    SecretKey key;

    public JwtAuthenticator(final SecretKey key) {
        this.key = key;
    }

    @Override
    public Optional<JwtToken> authenticate(final String token) {
        LOGGER.debug("Authenticating Token");
        try {
            Claims claims = Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

            String email = claims.get("Email", String.class);
            int roleId = claims.get("Role", Integer.class);

            JwtToken jwtToken = new JwtToken(email, new UserRole(roleId));
            LOGGER.info("Successfully authenticated Token");

            return Optional.of(jwtToken);
        } catch (Exception e) {
            LOGGER.error("Failed to authenticate Token");
            return Optional.empty();
        }
    }
}
