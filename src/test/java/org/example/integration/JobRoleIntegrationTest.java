package org.example.integration;

import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.example.TestApplication;
import org.example.TestConfiguration;
import org.example.models.JobRole;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Client;
import java.util.List;

@ExtendWith(DropwizardExtensionsSupport.class)
public class JobRoleIntegrationTest {
    private static final Logger LOGGER =
            LoggerFactory.getLogger(JobRoleIntegrationTest.class);

    public static final DropwizardAppExtension<TestConfiguration> APP =
            new DropwizardAppExtension<>(TestApplication.class);

    @Test
    void getJobRoles_shouldReturnListOfJobRoles() {
        LOGGER.info("Initialised Integration Test for GET /job-roles");
        Client client = APP.client();

        List<JobRole> response = client
                .target("http://localhost:8080/job-roles")
                .request()
                .get(List.class);

        LOGGER.debug("Response received {}", response);

        Assertions.assertFalse(response.isEmpty());
    }
}
