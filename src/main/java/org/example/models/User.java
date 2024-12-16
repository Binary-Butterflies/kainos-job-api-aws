package org.example.models;

public class User {
    String email;
    byte[] password;
    byte[] salt;

    public User(final String email,
                final byte[] password,
                final byte[] salt) {
        this.email = email;
        this.password = password;
        this.salt = salt;
    }

    public String getEmail() {
        return email;
    }

    public byte[] getPassword() {
        return password;
    }

    public byte[] getSalt() {
        return salt;
    }
}
