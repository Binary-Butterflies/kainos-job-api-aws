package org.example.daos;

import org.example.models.LoginRequest;
import org.example.models.RegisterRequest;
import org.example.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthDao {
    static final Logger LOGGER = LoggerFactory.getLogger(AuthDao.class);

    static final int EMAIL_PARAM_INDEX = 1;
    static final int PASSWORD_HASH_PARAM_INDEX = 2;
    static final int SALT_PARAM_INDEX = 3;

    public User getUser(final LoginRequest loginRequest) throws SQLException {
        try (Connection connection = DatabaseConnector.getConnection()) {
            String query = "SELECT email, passwordHash, salt "
                    + "FROM `user` WHERE BINARY email = ?;";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(EMAIL_PARAM_INDEX, loginRequest.getEmail());

            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.next()) {
                LOGGER.warn("Failed to find any users (email: {})",
                        loginRequest.getEmail());

                return null;
            }

            return new User(
                    resultSet.getString("email"),
                    resultSet.getBytes("passwordHash"),
                    resultSet.getBytes("salt")
            );
        }
    }

    public boolean registerUser(
            final RegisterRequest registerRequest,
            final byte[] passwordHash,
            final byte[] salt
    ) throws SQLException {
        try (Connection connection = DatabaseConnector.getConnection()) {
            String query = "INSERT INTO `user` "
                    + "(email, passwordHash, salt) "
                    + "VALUES (?, ?, ?);";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(EMAIL_PARAM_INDEX, registerRequest.getEmail());
            statement.setBytes(PASSWORD_HASH_PARAM_INDEX, passwordHash);
            statement.setBytes(SALT_PARAM_INDEX, salt);

            int modifiedRows = statement.executeUpdate();

            if (modifiedRows != 1) {
                LOGGER.warn("Failed to add User (Rows modified: {})",
                        modifiedRows);
                return false;
            }

            return true;
        }
    }

    public boolean doesUserExist(final String email)throws SQLException {
        try (Connection connection = DatabaseConnector.getConnection()) {
            String query = "SELECT 1 "
                    + "FROM `user` WHERE BINARY email = ?;";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(EMAIL_PARAM_INDEX, email);

            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        }
    }
}
