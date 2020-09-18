package helPet.dao;

import helPet.entity.Medication;
import helPet.entity.Pet;
import helPet.entity.util.EntityStatus;
import helPet.jdbi.BaseTest;
import org.jdbi.v3.core.Handle;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class MedicationDAOTest extends BaseTest {
    @Test
    public void testPhone() {
        Handle h = dbi.open();
        MedicationDAO medicationDAO = h.attach(MedicationDAO.class);

        Medication medication = new Medication();
        medication.setName(STR_SAMPLE_1);
        medication.setDescription(STR_SAMPLE_2);
        medication.setDose(STR_SAMPLE_3);
        medication.setStatus(EntityStatus.ACTIVE);
        medication.setCreatedBy(CREATED_BY);

        h.begin();

        long id = medicationDAO.insert(medication);
        medication.setId(id);

        Medication found = medicationDAO.findActive(medication.getId());
        assertNotNull(found);
        assertEquals(found.getName(), STR_SAMPLE_1);

        medication.setName(STR_SAMPLE_3);
        medication.setUpdatedBy(CREATED_BY);

        int updated = medicationDAO.update(medication);
        assertNotNull(updated);
        assertEquals(medication.getName(), STR_SAMPLE_3);

        int del = medicationDAO.remove(medication.getId(), CREATED_BY);
        assertNotNull(del);

        Medication deleted = medicationDAO.findActive(medication.getId());
        assertNull(deleted);

        h.rollback();
        h.close();
    }
}
