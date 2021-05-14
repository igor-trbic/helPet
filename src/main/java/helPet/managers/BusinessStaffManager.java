package helPet.managers;

import helPet.dao.AuthTokenAttributeDAO;
import helPet.dao.BusinessRoleDAO;
import helPet.dao.BusinessStaffDAO;
import helPet.dao.UserAuthTokenDAO;
import helPet.dao.UserDAO;
import helPet.dto.BusinessStaffDTO;
import helPet.entity.AuthTokenAttribute;
import helPet.entity.BusinessRole;
import helPet.entity.BusinessStaff;
import helPet.entity.User;
import helPet.entity.UserAuthToken;
import helPet.util.EmailSender;
import org.apache.commons.lang3.time.DateUtils;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BusinessStaffManager {
    private static final Logger LOG = LoggerFactory.getLogger(RegistrationManager.class);
    private Jdbi dbi;
    private HelPetSecurityManager helPetSecurityManager;

    public BusinessStaffManager(Jdbi dbi, HelPetSecurityManager helPetSecurityManager) {
        this.dbi=dbi;
        this.helPetSecurityManager = helPetSecurityManager;
    }

    public List<BusinessStaffDTO> get(Long businessId, User user) {
        Handle h = dbi.open();
        List<BusinessStaffDTO> businessStaffs = new ArrayList<>();
        try {
            h.begin();
            BusinessStaffDAO businessRoleTypeDAO = h.attach(BusinessStaffDAO.class);

            // TODO: Maybe include userId as well???
            businessStaffs = businessRoleTypeDAO.findByBusinessId(businessId);

            h.commit();
        } catch (Exception ex) {
            businessStaffs = null;
            LOG.error(ex.getMessage());
            h.rollback();
        } finally {
            h.close();
        }
        return businessStaffs;
    }

    public BusinessStaffDTO update(BusinessStaffDTO businessStaffDTO, User user) {
        Handle h = dbi.open();
        try {
            h.begin();
            // TODO: Maybe check if business exists at all
            BusinessRoleDAO businessRoleDAO = h.attach(BusinessRoleDAO.class);
            BusinessStaffDAO businessStaffDAO = h.attach(BusinessStaffDAO.class);

            BusinessStaff businessStaff = businessStaffDAO.findByUserId(businessStaffDTO.getId());
            if (businessStaff == null) {
                // TODO: implement client error
                throw new Exception("Cannot find staff");
            }

            BusinessRole businessRole = businessRoleDAO.findByStaffId(businessStaff.getId());
            businessRole.setBusinessRoleTypeId(businessStaffDTO.getBusinessRoleType().getId());
            businessRole.setUpdatedBy(user.getUsername());
            int flag = businessRoleDAO.update(businessRole);
            if (flag == 0) {
                throw new Exception("Cannot update staff role!");
            }

            h.commit();
        } catch (Exception ex) {
            businessStaffDTO = null;
            LOG.error(ex.getMessage());
            h.rollback();
        } finally {
            h.close();
        }
        return businessStaffDTO;
    }

    // TODO: Maybe implement removing staff from Business all together since staff without role is not really logical
//    public Boolean remove(Long id, User user) {
//        Boolean success = false;
//        Handle h = dbi.open();
//        try {
//            h.begin();
//            // TODO: Maybe check if business exists at all
//            BusinessStaffDAO businessStaffDAO = h.attach(BusinessStaffDAO.class);
//
////            long updatedFlag = businessRoleTypeDAO.remove(attrId, user.getUsername());
////            if (updatedFlag == 0) {
////                // TODO: implement client error
////                throw new Exception("Cannot insert pet attribute");
////            }
//            success = true;
//
//            h.commit();
//        } catch (Exception ex) {
//            success = false;
//            LOG.error(ex.getMessage());
//            h.rollback();
//        } finally {
//            h.close();
//        }
//        return success;
//    }

    public Boolean invite(BusinessStaffDTO businessStaffDTO, User user) {
        Boolean invited = false;

        Handle h = dbi.open();
        try {
            h.begin();
            UserDAO userDAO = h.attach(UserDAO.class);
            BusinessStaffDAO businessStaffDAO = h.attach(BusinessStaffDAO.class);
            UserAuthTokenDAO userAuthTokenDAO = h.attach(UserAuthTokenDAO.class);
            AuthTokenAttributeDAO authTokenAttributeDAO = h.attach(AuthTokenAttributeDAO.class);

            User existing = userDAO.findByUsername(businessStaffDTO.getUsername());
            if (existing != null) {
                BusinessStaff businessStaff = new BusinessStaff();
                businessStaff.setBusinessId(businessStaffDTO.getBusinessId());
                businessStaff.setUserId(existing.getId());
                long flag = businessStaffDAO.insert(businessStaff);
                if (flag == 0) {
                    throw new Exception("Cannot insert business staff");
                }
                invited = false;
            } else {

                UserAuthToken userAuthToken = new UserAuthToken();
                userAuthToken.setCreatedOn(new Date());
                String token = helPetSecurityManager.generateToken(businessStaffDTO.getUsername());
                userAuthToken.setToken(token);
                Date now = new Date();
                userAuthToken.setExpiryTime(DateUtils.addHours(now, 24));
                userAuthTokenDAO.insert(userAuthToken);

                AuthTokenAttribute staffRole = new AuthTokenAttribute();
                staffRole.setAttrName("staffRole");
                staffRole.setAttrValue(businessStaffDTO.getBusinessRoleType().getId().toString());
                staffRole.setCreatedBy(user.getUsername());
                staffRole.setTokenUserAuthToken(token);
                String flag = authTokenAttributeDAO.insert(staffRole);
                if (flag == null) {
                    throw new Exception("Cannot insert business staff");
                }

                // TODO: future me, please avoid this hacky way! :)
                EmailSender emailSender = new EmailSender();
                emailSender.sendMail(user.getUsername(), "Invitatoin", "localhost:4200/register?businessId=" + businessStaffDTO.getBusinessId() +
                        "&firstName=" + businessStaffDTO.getFirstName() +
                        "&lastName=" + businessStaffDTO.getLastName() +
                        "&email=" + businessStaffDTO.getUsername() +
                        "&token=" + token);
                invited = true;
            }
            h.commit();

        } catch (Exception ex) {
            LOG.error(ex.getMessage());
            h.rollback();
            invited = false;
        } finally {
            h.close();
        }
        return invited;
    }
}
