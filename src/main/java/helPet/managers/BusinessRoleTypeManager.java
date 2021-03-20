package helPet.managers;

import helPet.dao.BusinessDAO;
import helPet.dao.BusinessRoleTypeDAO;
import helPet.entity.Business;
import helPet.entity.BusinessRoleType;
import helPet.entity.User;
import helPet.entity.util.EntityStatus;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class BusinessRoleTypeManager {
    private static final Logger LOG = LoggerFactory.getLogger(RegistrationManager.class);
    private Jdbi dbi;

    public BusinessRoleTypeManager(Jdbi dbi) {
        this.dbi=dbi;
    }

    public List<BusinessRoleType> get(Long businessId, User user) {
        Handle h = dbi.open();
        List<BusinessRoleType> businessRoleTypes = new ArrayList<>();
        try {
            h.begin();
            BusinessRoleTypeDAO businessRoleTypeDAO = h.attach(BusinessRoleTypeDAO.class);

            // TODO: Maybe include userId as well???
            businessRoleTypes = businessRoleTypeDAO.findActiveByBusinessId(businessId);

            h.commit();
        } catch (Exception ex) {
            businessRoleTypes = null;
            LOG.error(ex.getMessage());
            h.rollback();
        } finally {
            h.close();
        }
        return businessRoleTypes;
    }

    public BusinessRoleType create(BusinessRoleType businessRoleType, User user) {
        Handle h = dbi.open();
        try {
            h.begin();
            BusinessDAO businessDAO = h.attach(BusinessDAO.class);
            BusinessRoleTypeDAO businessRoleTypeDAO = h.attach(BusinessRoleTypeDAO.class);

            Business business = businessDAO.findActive(businessRoleType.getBusinessId());
            if (business == null) {
                // TODO: implement client error
                throw new Exception("Cannot find business");
            }

            businessRoleType.setCreatedBy(user.getUsername());
            businessRoleType.setStatus(EntityStatus.ACTIVE);
            long businessRoleId = businessRoleTypeDAO.insert(businessRoleType);
            if (businessRoleId == 0) {
                throw new Exception("Cannot insert pet attribute");
            }
            businessRoleType.setId(businessRoleId);

            h.commit();
        } catch (Exception ex) {
            businessRoleType = null;
            LOG.error(ex.getMessage());
            h.rollback();
        } finally {
            h.close();
        }
        return businessRoleType;
    }

    public BusinessRoleType update(BusinessRoleType businessRoleType, User user) {
        Handle h = dbi.open();
        try {
            h.begin();
            // TODO: Maybe check if business exists at all
//            BusinessDAO businessDAO = h.attach(BusinessDAO.class);
            BusinessRoleTypeDAO businessRoleTypeDAO = h.attach(BusinessRoleTypeDAO.class);

            businessRoleType.setUpdatedBy(user.getUsername());
            long updatedFlag = businessRoleTypeDAO.update(businessRoleType);
            if (updatedFlag == 0) {
                // TODO: implement client error
                throw new Exception("Cannot insert pet attribute");
            }

            h.commit();
        } catch (Exception ex) {
            businessRoleType = null;
            LOG.error(ex.getMessage());
            h.rollback();
        } finally {
            h.close();
        }
        return businessRoleType;
    }

    public Boolean remove(Long businessId, Long attrId, User user) {
        Boolean success = false;
        Handle h = dbi.open();
        try {
            h.begin();
            // TODO: Maybe check if business exists at all
//            BusinessDAO businessDAO = h.attach(BusinessDAO.class);
            BusinessRoleTypeDAO businessRoleTypeDAO = h.attach(BusinessRoleTypeDAO.class);

            long updatedFlag = businessRoleTypeDAO.remove(attrId, user.getUsername());
            if (updatedFlag == 0) {
                // TODO: implement client error
                throw new Exception("Cannot insert pet attribute");
            }
            success = true;

            h.commit();
        } catch (Exception ex) {
            success = false;
            LOG.error(ex.getMessage());
            h.rollback();
        } finally {
            h.close();
        }
        return success;
    }
}
