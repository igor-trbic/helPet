package helPet;

import helPet.managers.SampleManager;
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
    private SampleManager sampleManager;

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

    public static SampleManager getRegistrationManager() {
        return INSTANCE.sampleManager;
    }

    public static void setRegistrationManager(SampleManager sampleManager) {
        INSTANCE.sampleManager = sampleManager;
    }
}
