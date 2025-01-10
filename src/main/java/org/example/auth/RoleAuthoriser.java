package org.example.auth;

import io.dropwizard.auth.Authorizer;
import org.example.models.JwtToken;

import javax.annotation.Nullable;
import javax.ws.rs.container.ContainerRequestContext;

public class RoleAuthoriser implements Authorizer<JwtToken> {
    @Override
    public boolean authorize(final JwtToken jwtToken, final String roleName) {
        try {
            return jwtToken.getUserRole().getRoleName().equals(roleName);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean authorize(
            final JwtToken principal,
            final String role,
            @Nullable final ContainerRequestContext requestContext) {
        try {
            return principal.getUserRole().getRoleName().equals(role);
        } catch (Exception e) {
            return false;
        }
    }
}
