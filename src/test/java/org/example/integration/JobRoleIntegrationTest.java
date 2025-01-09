package org.example.integration;

import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.example.TestApplication;
import org.example.TestConfiguration;
import org.example.exceptions.InvalidException;
import org.example.models.JobRole;
import org.example.services.AuthService;
import org.example.utils.TestAuthUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.HttpHeaders;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.util.List;

@ExtendWith(DropwizardExtensionsSupport.class)
public class JobRoleIntegrationTest {
    public static final DropwizardAppExtension<TestConfiguration> APP =
            new DropwizardAppExtension<>(TestApplication.class);

    String authToken = TestAuthUtils.generateTestAuthToken();

    public JobRoleIntegrationTest()
            throws SQLException, InvalidException, NoSuchAlgorithmException,
            InvalidKeySpecException {
    }

    @Test
    void getJobRoles_shouldReturnListOfJobRoles() {
        Client client = APP.client();

        List<JobRole> response = client
                .target("http://localhost:8080/job-roles")
                .request()
                .header(HttpHeaders.AUTHORIZATION, authToken)
                .get(List.class);

        Assertions.assertFalse(response.isEmpty());
    }
}
