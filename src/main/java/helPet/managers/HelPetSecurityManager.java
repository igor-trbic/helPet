package helPet.managers;

import com.google.common.hash.Hashing;
import helPet.dao.UserAuthTokenDAO;
import helPet.dao.UserDAO;
import helPet.entity.User;
import helPet.entity.UserAuthToken;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;

public class HelPetSecurityManager {
    private static final Logger LOG = LoggerFactory.getLogger(RegistrationManager.class);
    private Jdbi dbi;

    public HelPetSecurityManager(Jdbi dbi) {
        this.dbi=dbi;
    }

    public String generateToken(String username) {
        String hashed = "";

        try {
            StringBuilder sb = new StringBuilder();
            sb.append(username);
            sb.append(System.nanoTime());

             hashed = Hashing.sha256()
                    .hashString(sb.toString(), StandardCharsets.UTF_8)
                    .toString();
        } catch (Exception e) {
            LOG.error("Cannot create TOKEN!");
        }
        return hashed;
    }

    public boolean isValid(String token) {
        Handle h = dbi.open();
        boolean success = false;
        try {
            h.begin();
            UserAuthTokenDAO userAuthTokenDAO = h.attach(UserAuthTokenDAO.class);

            UserAuthToken userAuthToken = userAuthTokenDAO.findByToken(token);

            if (userAuthToken == null) {
                System.out.println("NO VALID TOKEN!");
                return false;
            }

            success = true;
            h.commit();

        } catch (Exception ex) {
            h.rollback();
        } finally {
            h.close();
        }
        return success;
    }

    public User getUserByToken(String token) {
        Handle h = dbi.open();
        User user = new User();
        try {
            h.begin();
            UserDAO userDAO = h.attach(UserDAO.class);
            UserAuthTokenDAO userAuthTokenDAO = h.attach(UserAuthTokenDAO.class);

            UserAuthToken userAuthToken = userAuthTokenDAO.findByToken(token);

            if (userAuthToken == null) {
                return user;
            }

            user = userDAO.findById(userAuthToken.getUserId());

            if (user == null) {
                System.out.println("NO USER FOUND, OH NO!");
            }

        } catch (Exception ex) {
            h.rollback();
        } finally {
            h.commit();
            h.close();
        }
        return user;
    }
}
