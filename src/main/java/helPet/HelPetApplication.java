package helPet;

import helPet.bundles.ComponentBundle;
import helPet.health.TemplateHealthCheck;
//import helPet.resources.HelloWorldResource;
import io.dropwizard.Application;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.jdbi.v3.core.Jdbi;

public class HelPetApplication extends Application<HelPetConfiguration> {

    private HelPetConfiguration helPetConfiguration = new HelPetConfiguration();

    public static void main(final String[] args) throws Exception {
        new HelPetApplication().run(args);
    }

    @Override
    public String getName() {
        return "helPet";
    }

    @Override
    public void initialize(final Bootstrap<HelPetConfiguration> bootstrap) {
        // TODO: application initialization
        bootstrap.addBundle(new ComponentBundle(helPetConfiguration));
    }

    @Override
    public void run(final HelPetConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
        final TemplateHealthCheck healthCheck =
                new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);
        final JdbiFactory factory = new JdbiFactory();
    }

}
