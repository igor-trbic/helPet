package helPet.dao;

import helPet.entity.Pet;
import helPet.entity.PetAttribute;
import helPet.entity.util.EntityStatus;
import helPet.jdbi.BaseTest;
import org.jdbi.v3.core.Handle;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class PetAttributeDAOTest extends BaseTest {
    @Test
    public void testUserAddress() {
        Handle h = dbi.open();
        PetDAO petDAO = h.attach(PetDAO.class);
        PetAttributeDAO petAttributeDAO = h.attach(PetAttributeDAO.class);

        Pet pet = new Pet();
        pet.setName(STR_SAMPLE_1);
        pet.setNote(STR_SAMPLE_2);
        pet.setDateOfBirth(new Date());
        pet.setStatus(EntityStatus.ACTIVE);
        pet.setCreatedBy(CREATED_BY);

        h.begin();

        long petId = petDAO.insert(pet);

        PetAttribute petAttribute =  new PetAttribute();
        petAttribute.setPetId(petId);
        petAttribute.setName(STR_SAMPLE_1);
        petAttribute.setValue(STR_SAMPLE_2);
        petAttribute.setCreatedBy(CREATED_BY);
        petAttribute.setStatus(EntityStatus.ACTIVE);

        long petAttributeId = petAttributeDAO.insert(petAttribute);
        assertNotNull(petAttributeId);
        petAttribute.setId(petAttributeId);

        PetAttribute found = petAttributeDAO.findActive(petAttribute.getId());
        assertNotNull(found);
        assertEquals(found.getName(), STR_SAMPLE_1);

        petAttribute.setName(STR_SAMPLE_3);
        petAttribute.setUpdatedBy(CREATED_BY);

        int updated = petAttributeDAO.update(petAttribute);
        assertNotNull(updated);
        assertEquals(petAttribute.getName(), STR_SAMPLE_3);

        int del = petAttributeDAO.remove(petAttribute.getId(), CREATED_BY);
        assertNotNull(del);

        PetAttribute deleted = petAttributeDAO.findActive(petAttribute.getId());
        assertNull(deleted);

        h.rollback();
        h.close();
    }
}
