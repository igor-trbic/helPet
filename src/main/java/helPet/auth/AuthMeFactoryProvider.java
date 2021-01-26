package helPet.auth;

import helPet.HelPetService;
import helPet.entity.User;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import org.glassfish.jersey.internal.inject.AbstractBinder;
import org.glassfish.jersey.server.ContainerRequest;
import org.glassfish.jersey.server.internal.inject.AbstractValueParamProvider;
import org.glassfish.jersey.server.internal.inject.MultivaluedParameterExtractorProvider;
import org.glassfish.jersey.server.model.Parameter;
import org.glassfish.jersey.server.spi.internal.ValueParamProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.lang.annotation.Annotation;
import java.security.Principal;
import java.util.Optional;
import java.util.function.Function;

@Singleton
public class AuthMeFactoryProvider<T extends Principal> extends AbstractValueParamProvider {
    private static final Logger log = LoggerFactory.getLogger(AuthMeFactoryProvider.class);

    @Context
    HttpServletRequest context;

    @Inject
    Authenticator authenticator;

    private final Class<T> principalClass;

    @Inject
    public AuthMeFactoryProvider(MultivaluedParameterExtractorProvider mpep,
                                 AuthMeFactoryProvider.PrincipalClassProvider<T> principalClassProvider) {
        super(() -> mpep, Parameter.Source.UNKNOWN);
        this.principalClass = principalClassProvider.clazz;
    }

    @Override
    protected Function<ContainerRequest, ?> createValueProvider(Parameter parameter) {
        if (parameter.getAnnotation(AuthMe.class) == null) {
            return null;
        }

        Annotation annotation = parameter.getSourceAnnotation();
        if (!(annotation instanceof AuthMe)) {
            return null;
        }

        return new AuthMeParamProvider();
    }

    private class AuthMeParamProvider implements Function<ContainerRequest, User>  {

        @Override
        public User apply(ContainerRequest containerRequest) {

            // Get the Authorization header
            try {

                String token = context.getHeader("helpet-token");

                if (token == null) {
                    throw new WebApplicationException(Response.Status.FORBIDDEN);
                }

                HelPetCredencials helPetCredencials = new HelPetCredencials(token);
                final Optional<User> result = authenticator.authenticate(helPetCredencials);
                if (result.isPresent()) {
                    return result.get();
                } else {
                    if (HelPetService.getHelPetSecurityManager().isValid(token)) {
                        throw new WebApplicationException(Response.Status.FORBIDDEN);
                    } else {
                        throw new WebApplicationException(Response.Status.UNAUTHORIZED);
                    }
                }
            } catch (IllegalArgumentException e) {
                log.debug("Error decoding credentials", e);
            } catch (AuthenticationException e) {
                log.warn("Error authenticating credentials", e);
                throw new WebApplicationException(Response.Status.FORBIDDEN);
            }

//              Must have failed to be here
            throw new WebApplicationException(Response.Status.FORBIDDEN);
        }
    }

    @Singleton
    static class PrincipalClassProvider<T extends Principal> {

        private final Class<T> clazz;

        PrincipalClassProvider(Class<T> clazz) {
            this.clazz = clazz;
        }
    }

    /**
            * Injection binder for {@link AuthMeFactoryProvider}.
            *
            * @param <T> the type of the principal
     */
    public static class Binder<T extends Principal> extends AbstractBinder {

        private final Class<T> principalClass;

        public Binder(Class<T> principalClass) {
            this.principalClass = principalClass;
        }

        @Override
        protected void configure() {
            bind(new AuthMeFactoryProvider.PrincipalClassProvider<>(principalClass)).to(AuthMeFactoryProvider.PrincipalClassProvider.class);
            bind(AuthMeFactoryProvider.class).to(ValueParamProvider.class).in(Singleton.class);
        }
    }


}
