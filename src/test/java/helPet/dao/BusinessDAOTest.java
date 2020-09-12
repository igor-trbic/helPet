package helPet.dao;

import helPet.entity.Address;
import helPet.entity.Business;
import helPet.entity.User;
import helPet.entity.util.EntityStatus;
import helPet.jdbi.BaseTest;
import org.jdbi.v3.core.Handle;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class BusinessDAOTest extends BaseTest {
    @Test
    public void testBusiness() {
        Handle h = dbi.open();
        UserDAO userDAO = h.attach(UserDAO.class);
        BusinessDAO businessDAO = h.attach(BusinessDAO.class);

        User user = new User();
        user.setUsername(STR_SAMPLE_1);
        user.setFirstName(STR_SAMPLE_2);
        user.setLastName(STR_SAMPLE_3);
        user.setPassword("f27f7q%$@#$%f/afasdfaf");
        user.setDateOfBirth(new Date());
        user.setCreatedOn(new Date());
        user.setCreatedBy(CREATED_BY);
        user.setStatus(EntityStatus.ACTIVE);

        h.begin();

        long userId = userDAO.insert(user);
        user.setId(userId);

        Business business = new Business();
        business.setBusinessOwnerId(user.getId());
        business.setBusinessName(STR_SAMPLE_1);
        business.setTaxId(STR_SAMPLE_2);
        business.setNationalId(STR_SAMPLE_3);
        business.setStatus(EntityStatus.ACTIVE);
        business.setCreatedBy(CREATED_BY);

        h.begin();

        long id = businessDAO.insert(business);
        business.setId(id);

        Business found = businessDAO.findActive(business.getId());
        assertNotNull(found);
        assertEquals(found.getBusinessName(), STR_SAMPLE_1);

        business.setBusinessName(STR_SAMPLE_3);
        business.setUpdatedBy(CREATED_BY);

        int updated = businessDAO.update(business);
        assertNotNull(updated);
        assertEquals(business.getBusinessName(), STR_SAMPLE_3);

        int del = businessDAO.remove(business.getId(), CREATED_BY);
        assertNotNull(del);

        Business deleted = businessDAO.findActive(business.getId());
        assertNull(deleted);

        h.rollback();
        h.close();
    }
}
