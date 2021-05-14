package helPet.managers;

import helPet.dao.BusinessDAO;
import helPet.entity.Business;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class BusinessManager {
    private static final Logger LOG = LoggerFactory.getLogger(RegistrationManager.class);
    private Jdbi dbi;

    public BusinessManager(Jdbi dbi) {
        this.dbi=dbi;
    }

    public List<Business> get() {
        Handle h = dbi.open();
        List<Business> businesses = new ArrayList<>();
        try {
            h.begin();
            BusinessDAO businessDAO = h.attach(BusinessDAO.class);

            // TODO: Maybe include userId as well???
            businesses = businessDAO.findAllActive();

            h.commit();
        } catch (Exception ex) {
            businesses = null;
            LOG.error(ex.getMessage());
            h.rollback();
        } finally {
            h.close();
        }
        return businesses;
    }
}
