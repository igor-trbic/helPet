package helPet.managers;

import helPet.dao.UserAuthTokenDAO;
import helPet.dao.UserDAO;
import helPet.entity.AuthReq;
import helPet.entity.User;
import helPet.entity.UserAuthToken;
import org.apache.commons.lang3.time.DateUtils;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class AuthManager {

    private static final Logger LOG = LoggerFactory.getLogger(RegistrationManager.class);
    private Jdbi dbi;
    private HelPetSecurityManager helPetSecurityManager;

    public AuthManager(Jdbi dbi, HelPetSecurityManager helPetSecurityManager) {
        this.dbi=dbi;
        this.helPetSecurityManager = helPetSecurityManager;
    }

    public User vaidate(AuthReq authReq) {
        User user = null;
        Handle h = dbi.open();
        try {
            h.begin();
            UserDAO userDAO = h.attach(UserDAO.class);

            user = userDAO.findByUsernameAndPassword(authReq.getUsername(), authReq.getPassword());

            if (user == null) {
                System.out.println("NO USER FOUND, OH NO!");
            }
            return user;

        } catch (Exception ex) {
            LOG.error(ex.getMessage());
            h.rollback();
        } finally {
            h.commit();
            h.close();
        }
        return user;
    }

    public String generateToken(User user) {
        Handle h = dbi.open();
        String token = "";
        try {
            h.begin();
            UserAuthTokenDAO userAuthTokenDAO = h.attach(UserAuthTokenDAO.class);

            // TODO: initial implementation, later on extend token or something similar
            UserAuthToken existing = userAuthTokenDAO.findByUserId(user.getId());
            if (existing != null) {
                userAuthTokenDAO.delete(existing.getUserId(), existing.getToken());
            }
            token = helPetSecurityManager.generateToken(user.getUsername());

            Date now  = new Date();
            UserAuthToken userAuthToken = new UserAuthToken();
            userAuthToken.setExpiryTime(DateUtils.addHours(now, 24));
            userAuthToken.setUserId(user.getId());
            userAuthToken.setToken(token);
            Long flag = userAuthTokenDAO.insert(userAuthToken);
            // TODO: error handling

            h.commit();
        } catch (Exception ex) {
            LOG.error(ex.getMessage());
            h.rollback();
        } finally {
            h.close();
        }
        return token;
    }
}
