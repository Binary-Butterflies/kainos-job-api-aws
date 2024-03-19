package org.example.services;

import org.example.daos.TestDao;

import java.sql.SQLException;
import java.util.List;

public class TestService {
    TestDao testDao;

    public TestService(TestDao testDao) {
        this.testDao = testDao;
    }

    public List<String> testConnection() throws SQLException {
        return testDao.testConnection();
    }
}
