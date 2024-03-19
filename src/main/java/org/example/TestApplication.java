package org.example;

import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;
import org.example.controllers.TestController;
import org.example.daos.TestDao;
import org.example.services.TestService;

public class TestApplication extends Application<TestConfiguration> {

    public static void main(final String[] args) throws Exception {
        new TestApplication().run(args);
    }

    @Override
    public String getName() {
        return "true";
    }

    @Override
    public void initialize(final Bootstrap<TestConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final TestConfiguration configuration,
                    final Environment environment) {
        environment.jersey().register(new TestController(new TestService(new TestDao())));
    }

}
