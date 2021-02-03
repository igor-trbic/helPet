package helPet.managers;

import helPet.dao.EmailDAO;
import helPet.entity.Email;
import helPet.entity.User;
import helPet.entity.util.EntityStatus;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class EmailManager {

    private static final Logger LOG = LoggerFactory.getLogger(RegistrationManager.class);
    private Jdbi dbi;

    public EmailManager(Jdbi dbi) {
        this.dbi=dbi;
    }

    public Email createEmail(Email email, User user) throws Exception {
        Handle h = dbi.open();
        try {
            h.begin();
            EmailDAO emailDAO = h.attach(EmailDAO.class);

            email.setCreatedBy(user.getUsername());
            email.setStatus(EntityStatus.ACTIVE);
            email.setUserId(user.getId());
            long inserted = emailDAO.insert(email);
            if (inserted == 0) {
                // TODO: implement client error
                throw new Exception("Cannot insert email");
            }
            email.setId(inserted);

            h.commit();
        } catch (Exception ex) {
            email = null;
            LOG.error(ex.getMessage());
            h.rollback();
        } finally {
            h.close();
        }
        return email;
    }

    public List<Email> getEmails(User user) {
        Handle h = dbi.open();
        List<Email> emails = new ArrayList<>();
        try {
            h.begin();
            EmailDAO emailDAO = h.attach(EmailDAO.class);

            emails = emailDAO.findByUserId(user.getId());

            h.commit();
        } catch (Exception ex) {
            emails = null;
            LOG.error(ex.getMessage());
            h.rollback();
        } finally {
            h.close();
        }
        return emails;
    }

    public Email updateEmail(Email email, User user) {
        Handle h = dbi.open();
        try {
            h.begin();
            EmailDAO emailDAO = h.attach(EmailDAO.class);

            email.setUpdatedBy(user.getUsername());
            long updatedFlag = emailDAO.update(email);
            if (updatedFlag == 0) {
                // TODO: implement client error
                throw new Exception("Cannot insert email");
            }

            h.commit();
        } catch (Exception ex) {
            email = null;
            LOG.error(ex.getMessage());
            h.rollback();
        } finally {
            h.close();
        }
        return email;
    }

    public Boolean removeEmail(Long id, User user) {
        Boolean success = false;
        Handle h = dbi.open();
        try {
            h.begin();
            EmailDAO emailDAO = h.attach(EmailDAO.class);

            long updatedFlag = emailDAO.remove(id, user.getUsername());
            if (updatedFlag == 0) {
                // TODO: implement client error
                throw new Exception("Cannot insert email");
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
