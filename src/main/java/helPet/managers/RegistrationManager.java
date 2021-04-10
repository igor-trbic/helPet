package helPet.managers;

import helPet.dao.AuthTokenAttributeDAO;
import helPet.dao.BusinessDAO;
import helPet.dao.BusinessRoleDAO;
import helPet.dao.BusinessStaffDAO;
import helPet.dao.EmailDAO;
import helPet.dao.UserAuthTokenDAO;
import helPet.dao.UserDAO;
import helPet.dto.BusinessRegisterDTO;
import helPet.dto.UserDTO;
import helPet.entity.AuthTokenAttribute;
import helPet.entity.Business;
import helPet.entity.BusinessRole;
import helPet.entity.BusinessStaff;
import helPet.entity.Email;
import helPet.entity.User;
import helPet.entity.UserAuthToken;
import helPet.entity.util.EntityStatus;
import helPet.util.EmailSender;
import org.apache.commons.lang3.time.DateUtils;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

import static helPet.constants.ApplicationConstants.SYSYEM_NAME;

public class RegistrationManager {

    private static final Logger LOG = LoggerFactory.getLogger(RegistrationManager.class);
    private Jdbi dbi;
    private HelPetSecurityManager helPetSecurityManager;

    public RegistrationManager(Jdbi dbi, HelPetSecurityManager helPetSecurityManager) {
        this.dbi=dbi;
        this.helPetSecurityManager = helPetSecurityManager;
    }

    public Boolean register(UserDTO userDTO, String tokenReg) throws Exception {
        Boolean success = false;

        Handle h = dbi.open();
        try {
            h.begin();
            UserDAO userDAO = h.attach(UserDAO.class);
            UserAuthTokenDAO userAuthTokenDAO = h.attach(UserAuthTokenDAO.class);
            EmailDAO emailDAO = h.attach(EmailDAO.class);
            BusinessStaffDAO businessStaffDAO = h.attach(BusinessStaffDAO.class);
            BusinessRoleDAO businessRoleDAO = h.attach(BusinessRoleDAO.class);
            AuthTokenAttributeDAO authTokenAttributeDAO = h.attach(AuthTokenAttributeDAO.class);

            // TODO: Encrypt pass
            userDTO.setStatus(EntityStatus.PENDING);
            userDTO.setDateOfBirth(new Date());
            userDTO.setCreatedBy(SYSYEM_NAME);
            long userId = userDAO.insert(userDTO);
            if (userId == 0) {
                throw new Exception("Cannot create user");
            }
            userDTO.setId(userId);

            UserAuthToken userAuthToken = new UserAuthToken();
            userAuthToken.setUserId(userDTO.getId());
            userAuthToken.setCreatedOn(new Date());
            String token = helPetSecurityManager.generateToken(userDTO.getUsername());
            userAuthToken.setToken(token);
            Date now = new Date();
            userAuthToken.setExpiryTime(DateUtils.addHours(now, 24));
            userAuthTokenDAO.insert(userAuthToken);

            Email email = new Email();
            email.setEmailAddress(userDTO.getUsername());
            email.setUserId(userDTO.getId());
            email.setEmailType("PERSONAL");
            email.setIsPrimary(true);
            email.setCreatedBy(SYSYEM_NAME);
            email.setStatus(EntityStatus.ACTIVE);
            long emailFlag = emailDAO.insert(email);
            if (emailFlag == 0) {
                throw new Exception("Cannot create email");
            }

            if (userDTO.getBusinessId() != null && tokenReg != null) {
                BusinessStaff businessStaff = new BusinessStaff();
                businessStaff.setBusinessId(userDTO.getBusinessId());
                businessStaff.setUserId(userDTO.getId());
                long flag = businessStaffDAO.insert(businessStaff);
                if (flag == 0) {
                    throw new Exception("Cannot insert business staff");
                }

                AuthTokenAttribute authTokenAttribute = authTokenAttributeDAO.findByTokenAndAttrName(tokenReg, "staffRole");
                if (authTokenAttribute == null) {
                    throw new Exception("Cannot find appropriate role");
                }

                BusinessRole businessRole = new BusinessRole();
                businessRole.setStatus(EntityStatus.ACTIVE);
                businessRole.setBusinessRoleTypeId(Long.valueOf(authTokenAttribute.getAttrValue()));
                businessRole.setBusinessStaffId(businessStaff.getId());
                businessRole.setCreatedBy(SYSYEM_NAME);
                long bflag = businessRoleDAO.insert(businessRole);
                if (bflag == 0) {
                    throw new Exception("Cannot insert business role");
                }
            }

            EmailSender emailSender = new EmailSender();
            // TODO: Create proper confirm registration page
            emailSender.sendMail(userDTO.getUsername(), "Registration", "localhost:8080/helpet/register/" + userAuthToken.getToken());

            h.commit();
            success = true;

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

    public Boolean registerBusiness(BusinessRegisterDTO businessRegisterDTO) {
        Boolean success = false;

        Handle h = dbi.open();
        try {
            h.begin();
            UserDAO userDAO = h.attach(UserDAO.class);
            UserAuthTokenDAO userAuthTokenDAO = h.attach(UserAuthTokenDAO.class);
            EmailDAO emailDAO = h.attach(EmailDAO.class);
            BusinessDAO businessDAO = h.attach(BusinessDAO.class);
            BusinessStaffDAO businessStaffDAO = h.attach(BusinessStaffDAO.class);

            // TODO: Encrypt pass
            businessRegisterDTO.getUser().setStatus(EntityStatus.PENDING);
            businessRegisterDTO.getUser().setDateOfBirth(new Date());
            businessRegisterDTO.getUser().setCreatedBy(SYSYEM_NAME);
            long userId = userDAO.insert(businessRegisterDTO.getUser());
            if (userId == 0) {
                throw new Exception("Cannot create user");
            }
            businessRegisterDTO.getUser().setId(userId);

            Business business = new Business();
            business.setTaxId(businessRegisterDTO.getBusiness().getTaxId());
            business.setNationalId(businessRegisterDTO.getBusiness().getNationalId());
            business.setBusinessOwnerId(businessRegisterDTO.getUser().getId());
            business.setBusinessName(businessRegisterDTO.getBusiness().getBusinessName());
            business.setStatus(EntityStatus.PENDING);
            business.setCreatedBy(SYSYEM_NAME);
            long businessId = businessDAO.insert(business);
            if (businessId == 0) {
                throw new Exception("Cannot create business");
            }
            businessRegisterDTO.getBusiness().setId(businessId);

            BusinessStaff businessStaff = new BusinessStaff();
            businessStaff.setUserId(businessRegisterDTO.getUser().getId());
            businessStaff.setBusinessId(businessRegisterDTO.getBusiness().getId());
            long businessStaffId = businessStaffDAO.insert(businessStaff);
            if (businessStaffId == 0) {
                throw new Exception("Cannot create business staff");
            }

            UserAuthToken userAuthToken = new UserAuthToken();
            userAuthToken.setUserId(businessRegisterDTO.getUser().getId());
            userAuthToken.setCreatedOn(new Date());
            String token = helPetSecurityManager.generateToken(businessRegisterDTO.getUser().getUsername());
            userAuthToken.setToken(token);
            Date now = new Date();
            userAuthToken.setExpiryTime(DateUtils.addHours(now, 24));
            userAuthTokenDAO.insert(userAuthToken);

            Email email = new Email();
            email.setEmailAddress(businessRegisterDTO.getUser().getUsername());
            email.setUserId(businessRegisterDTO.getUser().getId());
            email.setEmailType("PERSONAL");
            email.setIsPrimary(true);
            email.setStatus(EntityStatus.ACTIVE);
            email.setCreatedBy(SYSYEM_NAME);
            long emailFlag = emailDAO.insert(email);
            if (emailFlag == 0) {
                throw new Exception("Cannot create email");
            }

            EmailSender emailSender = new EmailSender();
            emailSender.sendMail(businessRegisterDTO.getUser().getUsername(), "Registration", "localhost:8080/helpet/register/" + userAuthToken.getToken());

            h.commit();
            success = true;

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
