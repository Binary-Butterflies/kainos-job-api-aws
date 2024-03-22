package org.example;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import org.example.controllers.TestController;
import org.example.daos.TestDao;
import org.example.services.TestService;

public class TestApplication extends Application<TestConfiguration> {

    /**
     * main.
     * @param args
     * @throws Exception
     */
    public static void main(final String[] args) throws Exception {
        new TestApplication().run(args);
    }

    /**
     * getName.
     * @return name
     */
    @Override
    public String getName() {
        return "Test";
    }

    /**
     * initialize.
     */
    @Override
    public void initialize(final Bootstrap<TestConfiguration> bootstrap) {
        bootstrap.addBundle(new SwaggerBundle<>() {
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(
                    final TestConfiguration configuration) {
                return configuration.getSwagger();
            }
        });
    }

    /**
     * run.
     */
    @Override
    public void run(final TestConfiguration configuration,
                    final Environment environment) {
        environment.jersey()
                .register(new TestController(new TestService(new TestDao())));
    }

}
