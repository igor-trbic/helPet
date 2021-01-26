package helPet.bundles;

import helPet.HelPetConfiguration;
import helPet.HelPetService;
import helPet.auth.AuthMeFactoryProvider;
import helPet.auth.HelPetAuthenticator;
import helPet.dao.common.EntityStatusAsIntArgFactory;
import helPet.entity.User;
import helPet.hk2.ImmediateFeature;
import helPet.managers.AuthManager;
import helPet.managers.PetManager;
import helPet.managers.RegistrationManager;
import helPet.managers.HelPetSecurityManager;
import helPet.resources.AuthResource;
import helPet.resources.PetResource;
import helPet.resources.RegistrationResource;
import io.dropwizard.ConfiguredBundle;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.db.DatabaseConfiguration;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.server.DefaultServerFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.glassfish.hk2.api.Immediate;
import org.glassfish.jersey.internal.inject.AbstractBinder;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.guava.GuavaPlugin;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class ComponentBundle <T extends HelPetConfiguration> implements ConfiguredBundle<T>, DatabaseConfiguration<T> {
    private static final Logger LOG = LoggerFactory.getLogger(ComponentBundle.class);

    private HelPetConfiguration pc;

    public ComponentBundle(HelPetConfiguration pc) {
        this.pc=pc;
    }

    @Override
    public void run(T configuration, Environment environment) throws Exception {
        LOG.info("run");
        final Jdbi dbi = configureDBI(environment, configuration.getDataSourceFactory());
        registerManagers(configuration, environment, dbi);

        ((DefaultServerFactory) configuration.getServerFactory()).setJerseyRootPath("/helpet/*");

        registerResources(environment);
        configureCors(configuration, environment);
    }

    @Override
    public void initialize(Bootstrap<?> bootstrap) {
    }

    @Override
    public DataSourceFactory getDataSourceFactory(HelPetConfiguration configuration) {
        return configuration.getDataSourceFactory();
    }

    private Jdbi configureDBI(Environment environment, DataSourceFactory dsf) {
        JdbiFactory factory = new JdbiFactory();
        Jdbi jdbi = factory.build(environment, dsf, "postgresql");
        jdbi.registerArgument(new EntityStatusAsIntArgFactory());
        jdbi.installPlugin(new SqlObjectPlugin());
        jdbi.installPlugin(new GuavaPlugin());

        return jdbi;
    }

    private void registerResources(Environment environment) {
        final Set<Object> resources = new HashSet<>();
        resources.add(new AuthResource(HelPetService.getAuthManager(), HelPetService.getHelPetSecurityManager()));
        resources.add(new PetResource(HelPetService.getPetManager()));
        resources.add(new RegistrationResource(HelPetService.getRegistrationManager()));

        for(Object r : resources){
            environment.jersey().register(r);
        }

    }

    private void registerManagers(final T configuration, Environment environment, Jdbi dbi) throws Exception {

        HelPetService.setEnvironment(environment);
        HelPetService.setConfiguration(configuration);
        HelPetService.setConfiguration(configuration);
        HelPetService.setDbi(dbi);

        HelPetService.setHelPetSecurityManager(new HelPetSecurityManager(dbi));
        HelPetService.setAuthManager(new AuthManager(dbi, HelPetService.getHelPetSecurityManager()));
        HelPetService.setRegistrationManager(new RegistrationManager(dbi));
        HelPetService.setPetManager(new PetManager(dbi));

        environment.jersey().getResourceConfig().register(ImmediateFeature.class);
        environment.jersey().getResourceConfig().register(new AbstractBinder() {

            @Override
            protected void configure() {
                bind(HelPetAuthenticator.class).to(Authenticator.class).in(Immediate.class);
            }
        });
        environment.jersey().getResourceConfig().register(new AuthMeFactoryProvider.Binder<>(User.class));
    }

    private void configureCors(T configuration, Environment environment) {
        FilterRegistration.Dynamic filter = environment.servlets().addFilter("CORS", CrossOriginFilter.class);
        filter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
        filter.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM, "GET,PUT,POST,DELETE,OPTIONS");
        filter.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, "*");
        filter.setInitParameter("accessControlAllowOrigin", "/*");
        filter.setInitParameter("allowedHeaders", "Content-Type,Authorization,X-Requested-With,Content-Length,Accept,Origin");
        filter.setInitParameter("Access-Control-Allow-Credentials", "true");
    }


}