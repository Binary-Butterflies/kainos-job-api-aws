package org.example.models;

import javax.security.auth.Subject;
import java.security.Principal;

public class JwtToken implements Principal {
    String email;

    public JwtToken(final String email) {
        this.email = email;
    }

    @Override
    public String getName() {
        return email;
    }

    @Override
    public boolean implies(final Subject subject) {
        return Principal.super.implies(subject);
    }
}
