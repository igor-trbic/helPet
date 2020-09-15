package helPet.dao;

import helPet.entity.Pet;
import helPet.entity.util.EntityStatus;
import helPet.jdbi.BaseTest;
import org.jdbi.v3.core.Handle;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class PetDAOTest extends BaseTest {
    @Test
    public void testPhone() {
        Handle h = dbi.open();
        PetDAO petDAO = h.attach(PetDAO.class);

        Pet pet = new Pet();
        pet.setName(STR_SAMPLE_1);
        pet.setNote(STR_SAMPLE_2);
        pet.setDateOfBirth(new Date());
        pet.setStatus(EntityStatus.ACTIVE);
        pet.setCreatedBy(CREATED_BY);

        h.begin();

        long id = petDAO.insert(pet);
        pet.setId(id);

        Pet found = petDAO.findActive(pet.getId());
        assertNotNull(found);
        assertEquals(found.getName(), STR_SAMPLE_1);

        pet.setName(STR_SAMPLE_3);
        pet.setUpdatedBy(CREATED_BY);

        int updated = petDAO.update(pet);
        assertNotNull(updated);
        assertEquals(pet.getName(), STR_SAMPLE_3);

        int del = petDAO.remove(pet.getId(), CREATED_BY);
        assertNotNull(del);

        Pet deleted = petDAO.findActive(pet.getId());
        assertNull(deleted);

        h.rollback();
        h.close();
    }
}
