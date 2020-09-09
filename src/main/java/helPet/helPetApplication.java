package helPet;

import helPet.health.TemplateHealthCheck;
//import helPet.resources.HelloWorldResource;
import io.dropwizard.Application;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.jdbi.v3.core.Jdbi;

public class helPetApplication extends Application<helPetConfiguration> {

    public static void main(final String[] args) throws Exception {
        new helPetApplication().run(args);
    }

    @Override
    public String getName() {
        return "helPet";
    }

    @Override
    public void initialize(final Bootstrap<helPetConfiguration> bootstrap) {
        // TODO: application initialization

    }

    @Override
    public void run(final helPetConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
//        final HelloWorldResource resource = new HelloWorldResource(
//                configuration.getTemplate(),
//                configuration.getDefaultName()
//        );
        final TemplateHealthCheck healthCheck =
                new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);
//        environment.jersey().register(resource);
        final JdbiFactory factory = new JdbiFactory();
        final Jdbi jdbi = factory.build(environment, configuration.getDataSourceFactory(), "postgresql");
//        environment.jersey().register(new UserResource(jdbi));
    }

}
