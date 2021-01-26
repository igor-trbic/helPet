package helPet;

import helPet.managers.AuthManager;
import helPet.managers.PetManager;
import helPet.managers.RegistrationManager;
import helPet.managers.HelPetSecurityManager;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Environment;
import org.jdbi.v3.core.Jdbi;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum HelPetService {

    INSTANCE;

    private ConcurrentHashMap<Long, Map<String, Thread>> handlersThreadsMap = new ConcurrentHashMap<>();

    private Environment environment;
    private Configuration configuration;
    private Jdbi dbi;
    private AuthManager authManager;
    private RegistrationManager registrationManager;
    private HelPetSecurityManager helPetSecurityManager;
    private PetManager petManager;

    public static Environment getEnvironment() {
        return INSTANCE.environment;
    }

    public static void setEnvironment(Environment environment) {
        INSTANCE.environment = environment;
    }

    public static Configuration getConfiguration() {
        return INSTANCE.configuration;
    }

    public static void setConfiguration(Configuration configuration) {
        INSTANCE.configuration = configuration;
    }

    public static Jdbi getDbi() {
        return INSTANCE.dbi;
    }

    public static void setDbi(Jdbi dbi) {
        INSTANCE.dbi = dbi;
    }

    public static AuthManager getAuthManager() {
        return INSTANCE.authManager;
    }

    public static void setAuthManager(AuthManager authManager) {
        INSTANCE.authManager = authManager;
    }

    public static RegistrationManager getRegistrationManager() {
        return INSTANCE.registrationManager;
    }

    public static void setRegistrationManager(RegistrationManager registrationManager) {
        INSTANCE.registrationManager = registrationManager;
    }

    public static HelPetSecurityManager getHelPetSecurityManager() {
        return INSTANCE.helPetSecurityManager;
    }

    public static void setHelPetSecurityManager(HelPetSecurityManager securityManager) {
        INSTANCE.helPetSecurityManager = securityManager;
    }

    public static PetManager getPetManager() {
        return INSTANCE.petManager;
    }

    public static void setPetManager(PetManager petManager) {
        INSTANCE.petManager = petManager;
    }
}
