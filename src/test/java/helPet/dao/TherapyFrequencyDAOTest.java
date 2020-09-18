package helPet.dao;

import helPet.entity.Therapy;
import helPet.entity.TherapyFrequency;
import helPet.entity.util.EntityStatus;
import helPet.jdbi.BaseTest;
import org.jdbi.v3.core.Handle;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class TherapyFrequencyDAOTest extends BaseTest {
    @Test
    public void testUserAddress() {
        Handle h = dbi.open();
        TherapyDAO therapyDAO = h.attach(TherapyDAO.class);
        TherapyFrequencyDAO therapyFrequencyDAO = h.attach(TherapyFrequencyDAO.class);

        Therapy therapy = new Therapy();
        therapy.setStatus(EntityStatus.ACTIVE);
        therapy.setCreatedBy(CREATED_BY);

        h.begin();

        long therapyId = therapyDAO.insert(therapy);
        assertNotNull(therapyId);
        therapy.setId(therapyId);

        TherapyFrequency therapyFrequency = new TherapyFrequency();
        therapyFrequency.setTherapyId(therapyId);
        therapyFrequency.setTimesPer("DAY");
        therapyFrequency.setTimes(1);
        therapyFrequency.setDescription(STR_SAMPLE_1);
        therapyFrequency.setThruDate(new Date());
        therapyFrequency.setFromDate(new Date());
        therapyFrequency.setCreatedBy(CREATED_BY);
        therapyFrequency.setStatus(EntityStatus.ACTIVE);

        long therapyFrequencyId = therapyFrequencyDAO.insert(therapyFrequency);
        assertNotNull(therapyFrequencyId);
        therapyFrequency.setId(therapyFrequencyId);

        TherapyFrequency found = therapyFrequencyDAO.findActive(therapyFrequencyId);
        assertNotNull(found);
        assertEquals(found.getDescription(), STR_SAMPLE_1);

        therapyFrequency.setTimesPer("WEEK");
        int update = therapyFrequencyDAO.update(therapyFrequency);
        assertNotNull(update);

        TherapyFrequency updated = therapyFrequencyDAO.findActive(therapyFrequencyId);
        assertNotNull(updated);
        assertEquals(updated.getTimesPer(), "WEEK");

        long deleted = therapyFrequencyDAO.remove(therapyFrequency.getId(), CREATED_BY);
        assertNotNull(deleted);

        TherapyFrequency delTerapyFrequenct = therapyFrequencyDAO.findActive(therapyFrequency.getId());
        assertNull(delTerapyFrequenct);

        h.rollback();
        h.close();
    }
}
