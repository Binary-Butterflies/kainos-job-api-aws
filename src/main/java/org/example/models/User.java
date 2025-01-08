package org.example.models;

public class User {
    final String email;
    final byte[] passwordHash;
    final byte[] salt;

    public User(final String email,
                final byte[] passwordHash,
                final byte[] salt) {
        this.email = email;
        this.passwordHash = passwordHash;
        this.salt = salt;
    }

    public String getEmail() {
        return email;
    }

    public byte[] getPasswordHash() {
        return passwordHash;
    }

    public byte[] getSalt() {
        return salt;
    }
}
