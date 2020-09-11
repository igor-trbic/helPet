package helPet.dao;

import helPet.entity.Phone;
import helPet.entity.util.EntityStatus;
import helPet.jdbi.BaseTest;
import org.jdbi.v3.core.Handle;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class PhoneDAOTest extends BaseTest {
    @Test
    public void testPhone() {
        Handle h = dbi.open();
        PhoneDAO phoneDAO = h.attach(PhoneDAO.class);

        Phone phone = new Phone();
        phone.setPhoneNumber(STR_SAMPLE_1);
        phone.setPhoneType(STR_SAMPLE_2);
        phone.setStatus(EntityStatus.ACTIVE);
        phone.setCreatedBy(CREATED_BY);

        h.begin();

        long id = phoneDAO.insert(phone);
        phone.setId(id);

        Phone found = phoneDAO.findActive(phone.getId());
        assertNotNull(found);
        assertEquals(found.getPhoneNumber(), STR_SAMPLE_1);

        phone.setPhoneNumber(STR_SAMPLE_3);
        phone.setUpdatedBy(CREATED_BY);

        int updated = phoneDAO.update(phone);
        assertNotNull(updated);
        assertEquals(phone.getPhoneNumber(), STR_SAMPLE_3);

        int del = phoneDAO.remove(phone.getId(), CREATED_BY);
        assertNotNull(del);

        Phone deleted = phoneDAO.findActive(phone.getId());
        assertNull(deleted);

        h.rollback();
        h.close();
    }
}
