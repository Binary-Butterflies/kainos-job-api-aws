package org.example.services;

import org.example.daos.TestDao;

import java.sql.SQLException;
import java.util.List;

public class TestService {
    TestDao testDao;

    /**
     * Constructor.
     * @param testDao
     */
    public TestService(final TestDao testDao) {
        this.testDao = testDao;
    }

    /**
     * testConnection.
     * @return List
     * @throws SQLException
     */
    public List<String> testConnection() throws SQLException {
        return testDao.testConnection();
    }
}
