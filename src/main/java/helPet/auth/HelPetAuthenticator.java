package helPet.auth;

import helPet.HelPetService;
import helPet.entity.User;
import helPet.managers.HelPetSecurityManager;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;

import java.util.Optional;

public class HelPetAuthenticator implements Authenticator<HelPetCredencials, User> {

    @Override
    public Optional<User> authenticate(HelPetCredencials credentials) throws AuthenticationException {

        HelPetSecurityManager securityManager = HelPetService.getHelPetSecurityManager();

        if (credentials.getToken() != null) {
            User user = securityManager.getUserByToken(credentials.getToken());
            if (user == null) {
                return Optional.empty();
            }
            return Optional.of(user);
        }
        return Optional.empty();
    }
}
