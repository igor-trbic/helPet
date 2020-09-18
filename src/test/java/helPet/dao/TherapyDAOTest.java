package helPet.dao;

import helPet.entity.Therapy;
import helPet.entity.util.EntityStatus;
import helPet.jdbi.BaseTest;
import org.jdbi.v3.core.Handle;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class TherapyDAOTest extends BaseTest {
    @Test
    public void testUserAddress() {
        Handle h = dbi.open();
        TherapyDAO therapyDAO = h.attach(TherapyDAO.class);

        Therapy therapy = new Therapy();
        therapy.setStatus(EntityStatus.ACTIVE);
        therapy.setCreatedBy(CREATED_BY);

        h.begin();

        long therapyId = therapyDAO.insert(therapy);
        assertNotNull(therapyId);
        therapy.setId(therapyId);

        Therapy found = therapyDAO.findActive(therapyId);
        assertNotNull(found);

        therapy.setStatus(EntityStatus.PENDING);
        int updated = therapyDAO.update(therapy);
        assertNotNull(updated);

        long deleted = therapyDAO.remove(therapyId, CREATED_BY);
        assertNotNull(deleted);
        Therapy deletedTerapy = therapyDAO.findActive(therapyId);
        assertNull(deletedTerapy);

        h.rollback();
        h.close();
    }
}
