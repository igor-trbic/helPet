package helPet;

import helPet.auth.HelPetAuthenticator;
import helPet.auth.HelPetAuthorizer;
import helPet.bundles.ComponentBundle;
import helPet.entity.User;
import helPet.health.TemplateHealthCheck;
//import helPet.resources.HelloWorldResource;
import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.auth.oauth.OAuthCredentialAuthFilter;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
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
//        final HelloWorldResource resource = new HelloWorldResource(
//                configuration.getTemplate(),
//                configuration.getDefaultName()
//        );
        final TemplateHealthCheck healthCheck =
                new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);
//        environment.jersey().register(resource);
        final JdbiFactory factory = new JdbiFactory();
        environment.jersey().register(new AuthDynamicFeature(
                new BasicCredentialAuthFilter.Builder<User>()
                        .setAuthenticator(new HelPetAuthenticator())
                        .setAuthorizer(new HelPetAuthorizer())
                        .setRealm("SUPER SECRET STUFF")
                        .buildAuthFilter()));
        environment.jersey().register(RolesAllowedDynamicFeature.class);
        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(User.class));
//        final Jdbi jdbi = factory.build(environment, configuration.getDataSourceFactory(), "postgresql");
//        environment.jersey().register(new UserResource(jdbi));
    }

}
