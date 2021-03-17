package helPet.managers;

import helPet.dao.BusinessDAO;
import helPet.dao.BusinessStaffDAO;
import helPet.dao.UserAuthTokenDAO;
import helPet.dao.UserDAO;
import helPet.dto.UserDTO;
import helPet.entity.AuthReq;
import helPet.entity.Business;
import helPet.entity.BusinessStaff;
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

    public UserDTO validate(UserDTO userDTO) {
        UserDTO authUser = new UserDTO();
        Handle h = dbi.open();
        try {
            h.begin();
            UserDAO userDAO = h.attach(UserDAO.class);
            BusinessDAO businessDAO = h.attach(BusinessDAO.class);
            BusinessStaffDAO businessStaffDAO = h.attach(BusinessStaffDAO.class);

            User user =  new User();
            user = userDAO.findByUsernameAndPassword(userDTO.getUsername(), userDTO.getPassword());

            if (user == null) {
                System.out.println("NO USER FOUND, OH NO!");
            }
            authUser.setDateOfBirth(user.getDateOfBirth());
            authUser.setFirstName(user.getFirstName());
            authUser.setLastName(user.getLastName());
            authUser.setUsername(user.getUsername());
            authUser.setId(user.getId());
            authUser.setCreatedBy(user.getCreatedBy());
            authUser.setCreatedOn(user.getCreatedOn());
            authUser.setStatus(user.getStatus());

            // Check if user works in some of the businesses
            BusinessStaff businessStaff = businessStaffDAO.findByUserId(user.getId());
            if (businessStaff != null) {
                Business business = businessDAO.findActive(businessStaff.getBusinessId());
                if (business != null) {
                    authUser.setBusinessId(business.getId());
                    authUser.setBusinessName(business.getBusinessName());
                }
            }

            return authUser;

        } catch (Exception ex) {
            LOG.error(ex.getMessage());
            h.rollback();
        } finally {
            h.commit();
            h.close();
        }
        return authUser;
    }

    public String generateToken(UserDTO user) {
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
