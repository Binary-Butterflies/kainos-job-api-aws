package org.example.models;

public class User {
    final String email;
    final byte[] passwordHash;
    final byte[] salt;
    final int roleId;

    public User(final String email,
                final byte[] passwordHash,
                final byte[] salt,
                final int roleId) {
        this.email = email;
        this.passwordHash = passwordHash;
        this.salt = salt;
        this.roleId = roleId;
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

    public int getRoleId() {
        return roleId;
    }
}
