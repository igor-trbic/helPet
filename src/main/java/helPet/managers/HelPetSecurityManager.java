package helPet.managers;

import helPet.dao.UserDAO;
import helPet.entity.User;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelPetSecurityManager {
    private static final Logger LOG = LoggerFactory.getLogger(RegistrationManager.class);
    private Jdbi dbi;

    public HelPetSecurityManager(Jdbi dbi) {
        this.dbi=dbi;
    }

    public User doAuth (String username, String password) {
        Handle h = dbi.open();
        User user = new User();
        try {
            h.begin();
            UserDAO userDAO = h.attach(UserDAO.class);

            user = userDAO.findByUsernameAndPassword(username, password);

            if (user == null) {
                System.out.println("NO USER FOUND, OH NO!");
            }

            h.commit();

        } catch (Exception ex) {
            h.rollback();
        } finally {
            h.close();
        }
        return user;
    }
}
