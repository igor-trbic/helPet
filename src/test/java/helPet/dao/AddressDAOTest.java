package helPet.dao;

import helPet.entity.Address;
import helPet.entity.util.EntityStatus;
import helPet.jdbi.BaseTest;
import org.jdbi.v3.core.Handle;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class AddressDAOTest extends BaseTest {
    @Test
    public void createUser() {
        Handle h = dbi.open();
        AddressDAO addressDAO = h.attach(AddressDAO.class);

        Address address = new Address();
        address.setStreetName(STR_SAMPLE_1);
        address.setHouseNumber("123");
        address.setAddressType("PERSONAL");
        address.setPostalCode(STR_SAMPLE_2);
        address.setStatus(EntityStatus.ACTIVE);
        address.setCreatedBy(CREATED_BY);

        h.begin();

        long id = addressDAO.insert(address);
        address.setId(id);

        Address found = addressDAO.find(address.getId());
        assertNotNull(found);
        assertEquals(found.getStreetName(), STR_SAMPLE_1);

        address.setStreetName(STR_SAMPLE_3);
        address.setUpdatedBy(CREATED_BY);

        int updated = addressDAO.update(address);
        assertNotNull(updated);
        assertEquals(address.getStreetName(), STR_SAMPLE_3);

        int del = addressDAO.remove(address.getId(), CREATED_BY);
        assertNotNull(del);

        Address deleted = addressDAO.find(address.getId());
        assertNull(deleted);

        h.rollback();
        h.close();
    }
}
