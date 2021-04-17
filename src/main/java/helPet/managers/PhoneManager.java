package helPet.managers;

import helPet.dao.BusinessPhoneDAO;
import helPet.dao.PhoneDAO;
import helPet.dao.UserPhoneDAO;
import helPet.entity.BusinessPhone;
import helPet.entity.Phone;
import helPet.entity.User;
import helPet.entity.UserPhone;
import helPet.entity.util.EntityStatus;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class PhoneManager {

    private static final Logger LOG = LoggerFactory.getLogger(RegistrationManager.class);
    private Jdbi dbi;

    public PhoneManager(Jdbi dbi) {
        this.dbi=dbi;
    }

    public Phone createPhone(Phone phone, User user) throws Exception {
        Handle h = dbi.open();
        try {
            h.begin();
            PhoneDAO phoneDAO = h.attach(PhoneDAO.class);
            UserPhoneDAO userPhoneDAO = h.attach(UserPhoneDAO.class);

            phone.setCreatedBy(user.getUsername());
            phone.setStatus(EntityStatus.ACTIVE);
            long inserted = phoneDAO.insert(phone);
            if (inserted == 0) {
                // TODO: implement client error
                throw new Exception("Cannot insert phone");
            }
            phone.setId(inserted);

            UserPhone userPhone = new UserPhone();
            userPhone.setPhoneId(phone.getId());
            userPhone.setUserId(user.getId());
            long insertedOwner = userPhoneDAO.insert(userPhone);
            if (insertedOwner == 0) {
                // TODO: implement client error
                throw new Exception("Cannot insert phone");
            }

            h.commit();
        } catch (Exception ex) {
            phone = null;
            LOG.error(ex.getMessage());
            h.rollback();
        } finally {
            h.close();
        }
        return phone;
    }

    public List<Phone> getPhones(User user) {
        Handle h = dbi.open();
        List<Phone> phones = new ArrayList<>();
        try {
            h.begin();
            PhoneDAO phoneDAO = h.attach(PhoneDAO.class);

            phones = phoneDAO.findByUserId(user.getId());

            h.commit();
        } catch (Exception ex) {
            phones = null;
            LOG.error(ex.getMessage());
            h.rollback();
        } finally {
            h.close();
        }
        return phones;
    }

    public Phone updatePhone(Phone phone, User user) {
        Handle h = dbi.open();
        try {
            h.begin();
            PhoneDAO phoneDAO = h.attach(PhoneDAO.class);

            phone.setUpdatedBy(user.getUsername());
            long updatedFlag = phoneDAO.update(phone);
            if (updatedFlag == 0) {
                // TODO: implement client error
                throw new Exception("Cannot insert phone");
            }

            h.commit();
        } catch (Exception ex) {
            phone = null;
            LOG.error(ex.getMessage());
            h.rollback();
        } finally {
            h.close();
        }
        return phone;
    }

    public Boolean removePhone(Long id, User user) {
        Boolean success = false;
        Handle h = dbi.open();
        try {
            h.begin();
            PhoneDAO phoneDAO = h.attach(PhoneDAO.class);

            long updatedFlag = phoneDAO.remove(id, user.getUsername());
            if (updatedFlag == 0) {
                // TODO: implement client error
                throw new Exception("Cannot insert phone");
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

    public Phone createBusinessPhone(Phone phone, Long businessId, User user) throws Exception {
        Handle h = dbi.open();
        try {
            h.begin();
            PhoneDAO phoneDAO = h.attach(PhoneDAO.class);
            BusinessPhoneDAO businessPhoneDAO = h.attach(BusinessPhoneDAO.class);

            phone.setCreatedBy(user.getUsername());
            phone.setStatus(EntityStatus.ACTIVE);
            long inserted = phoneDAO.insert(phone);
            if (inserted == 0) {
                // TODO: implement client error
                throw new Exception("Cannot insert phone");
            }
            phone.setId(inserted);

            BusinessPhone userPhone = new BusinessPhone();
            userPhone.setPhoneId(phone.getId());
            userPhone.setBusinessId(businessId);
            long insertedOwner = businessPhoneDAO.insert(userPhone);
            if (insertedOwner == 0) {
                // TODO: implement client error
                throw new Exception("Cannot insert phone");
            }

            h.commit();
        } catch (Exception ex) {
            phone = null;
            LOG.error(ex.getMessage());
            h.rollback();
        } finally {
            h.close();
        }
        return phone;
    }

    public List<Phone> getBusinessPhones(Long id, User user) {
        Handle h = dbi.open();
        List<Phone> phones = new ArrayList<>();
        try {
            h.begin();
            PhoneDAO phoneDAO = h.attach(PhoneDAO.class);

            phones = phoneDAO.findByBusinessId(id);

            h.commit();
        } catch (Exception ex) {
            phones = null;
            LOG.error(ex.getMessage());
            h.rollback();
        } finally {
            h.close();
        }
        return phones;
    }
}
