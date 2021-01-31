package helPet.managers;

import helPet.dao.EmailDAO;
import helPet.dao.UserAuthTokenDAO;
import helPet.dao.UserDAO;
import helPet.entity.Email;
import helPet.entity.User;
import helPet.entity.UserAuthToken;
import helPet.entity.util.EntityStatus;
import helPet.util.EmailSender;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

import static helPet.constants.ApplicationConstants.SYSYEM_NAME;

public class RegistrationManager {

    private static final Logger LOG = LoggerFactory.getLogger(RegistrationManager.class);
    private Jdbi dbi;

    public RegistrationManager(Jdbi dbi) {
        this.dbi=dbi;
    }

    public Boolean register(User user) {
        Boolean success = false;

        Handle h = dbi.open();
        try {
            h.begin();
            UserDAO userDAO = h.attach(UserDAO.class);
            UserAuthTokenDAO userAuthTokenDAO = h.attach(UserAuthTokenDAO.class);
            EmailDAO emailDAO = h.attach(EmailDAO.class);

            // TODO: Encrypt pass
            user.setStatus(EntityStatus.PENDING);
            user.setDateOfBirth(new Date());
            user.setCreatedBy(SYSYEM_NAME);
            long userId = userDAO.insert(user);
            user.setId(userId);

            UserAuthToken userAuthToken = new UserAuthToken();
            userAuthToken.setUserId(user.getId());
            userAuthToken.setCreatedOn(new Date());
            // TODO: generate token based on username/date
            userAuthToken.setToken("1234567890");
            userAuthToken.setExpiryTime(new Date());
            userAuthTokenDAO.insert(userAuthToken);
            success = true;

            Email email = new Email();
            email.setEmailAddress(user.getUsername());
            email.setUserId(user.getId());
            email.setEmailType("PERSONAL");
            email.setIsPrimary(true);
            email.setCreatedBy(SYSYEM_NAME);

            EmailSender emailSender = new EmailSender();
            emailSender.sendMail(user.getUsername(), "Registration", "localhost:8080/helpet/register/" + userAuthToken.getToken());

            h.commit();

        } catch (Exception ex) {
            LOG.error(ex.getMessage());
            h.rollback();
            success = false;
        } finally {
            h.close();
        }
        return success;
    }

    public Boolean confirmRegistration(String token) {
        Boolean success = false;

        Handle h = dbi.open();
        try {
            UserAuthTokenDAO userAuthTokenDAO = h.attach(UserAuthTokenDAO.class);
            UserDAO userDAO = h.attach(UserDAO.class);
            h.begin();

            UserAuthToken userAuthToken = userAuthTokenDAO.findByToken(token);
            if (userAuthToken != null) {
                User user = userDAO.findActive(userAuthToken.getUserId());
                user.setStatus(EntityStatus.ACTIVE);
                user.setUpdatedBy(SYSYEM_NAME);
                userDAO.update(user);
                // TODO: check if user updated
                success = true;
            } else {
                success = false;
            }

            h.commit();

        } catch (Exception ex) {
            LOG.error(ex.getMessage());
            h.rollback();
            success = false;
        } finally {
            h.close();
        }
        return success;
    }
}
