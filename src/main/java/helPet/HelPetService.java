package helPet;

import helPet.managers.AddressManager;
import helPet.managers.AppointmentManager;
import helPet.managers.AuthManager;
import helPet.managers.BusinessManager;
import helPet.managers.BusinessRoleTypeManager;
import helPet.managers.BusinessStaffManager;
import helPet.managers.EmailManager;
import helPet.managers.PetAttributeManager;
import helPet.managers.PetManager;
import helPet.managers.PhoneManager;
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
    private PetAttributeManager petAttributeManager;
    private PhoneManager phoneManager;
    private AddressManager addressManager;
    private EmailManager emailManager;
    private BusinessRoleTypeManager businessRoleTypeManager;
    private BusinessStaffManager businessStaffManager;
    private BusinessManager businessManager;
    private AppointmentManager appointmentManager;

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

    public static PhoneManager getPhoneManager() {
        return INSTANCE.phoneManager;
    }

    public static void setPhoneManager(PhoneManager phoneManager) {
        INSTANCE.phoneManager = phoneManager;
    }

    public static AddressManager getAddressManager() {
        return INSTANCE.addressManager;
    }

    public static void setAddressManager(AddressManager addressManager) {
        INSTANCE.addressManager = addressManager;
    }

    public static EmailManager getEmailManager() {
        return INSTANCE.emailManager;
    }

    public static void setEmailManager(EmailManager emailManager) {
        INSTANCE.emailManager = emailManager;
    }

    public static PetAttributeManager getPetAttributeManager() {
        return INSTANCE.petAttributeManager;
    }

    public static void setPetAttributeManager(PetAttributeManager petAttributeManager) {
        INSTANCE.petAttributeManager = petAttributeManager;
    }

    public static BusinessRoleTypeManager getBusinessRoleTypeManager() {
        return INSTANCE.businessRoleTypeManager;
    }

    public static void setBusinessRoleTypeManager(BusinessRoleTypeManager businessRoleTypeManager) {
        INSTANCE.businessRoleTypeManager = businessRoleTypeManager;
    }

    public static BusinessStaffManager getBusinessStaffManager() {
        return INSTANCE.businessStaffManager;
    }

    public static void setBusinessStaffManager(BusinessStaffManager businessStaffManager) {
        INSTANCE.businessStaffManager = businessStaffManager;
    }

    public static BusinessManager getBusinessManager() {
        return INSTANCE.businessManager;
    }

    public static void setBusinessManager(BusinessManager businessManager) {
        INSTANCE.businessManager = businessManager;
    }

    public static AppointmentManager getAppointmentManager() {
        return INSTANCE.appointmentManager;
    }

    public static void setAppointmentManager(AppointmentManager appointmentManager) {
        INSTANCE.appointmentManager = appointmentManager;
    }
}
