package helPet.auth;

import helPet.HelPetService;
import helPet.entity.User;
import helPet.managers.HelPetSecurityManager;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;

import java.util.Optional;

public class HelPetAuthenticator implements Authenticator <BasicCredentials, User> {

    @Override
    public Optional<User> authenticate(BasicCredentials credentials) throws AuthenticationException {

        HelPetSecurityManager securityManager = HelPetService.getSecurityManager();

        if ("secret".equals(credentials.getPassword())) {
//            return Optional.of(new User(credentials.getUsername()));
        }
        return Optional.empty();
    }
}
