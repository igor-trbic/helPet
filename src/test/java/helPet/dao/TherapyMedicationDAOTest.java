package helPet.dao;

import helPet.entity.Medication;
import helPet.entity.Therapy;
import helPet.entity.TherapyMedication;
import helPet.entity.util.EntityStatus;
import helPet.jdbi.BaseTest;
import org.jdbi.v3.core.Handle;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class TherapyMedicationDAOTest extends BaseTest {
    @Test
    public void testUserAddress() {
        Handle h = dbi.open();
        TherapyDAO therapyDAO = h.attach(TherapyDAO.class);
        TherapyMedicationDAO therapyMedicationDAO = h.attach(TherapyMedicationDAO.class);
        MedicationDAO medicationDAO = h.attach(MedicationDAO.class);

        Medication medication = new Medication();
        medication.setName(STR_SAMPLE_1);
        medication.setDescription(STR_SAMPLE_2);
        medication.setDose(STR_SAMPLE_3);
        medication.setStatus(EntityStatus.ACTIVE);
        medication.setCreatedBy(CREATED_BY);

        Therapy therapy = new Therapy();
        therapy.setStatus(EntityStatus.ACTIVE);
        therapy.setCreatedBy(CREATED_BY);

        h.begin();

        long therapyId = therapyDAO.insert(therapy);
        assertNotNull(therapyId);
        therapy.setId(therapyId);
        long medicationId = medicationDAO.insert(medication);
        assertNotNull(medicationId);
        medication.setId(medicationId);

        TherapyMedication therapyMedication = new TherapyMedication();
        therapyMedication.setTherapyId(therapyId);
        therapyMedication.setMedicationId(medicationId);
        therapyMedication.setTimesPer("DAY");
        therapyMedication.setTimes(1);
        therapyMedication.setDescription(STR_SAMPLE_1);
        therapyMedication.setThruDate(new Date());
        therapyMedication.setFromDate(new Date());
        therapyMedication.setCreatedBy(CREATED_BY);
        therapyMedication.setStatus(EntityStatus.ACTIVE);

        long therapyFrequencyId = therapyMedicationDAO.insert(therapyMedication);
        assertNotNull(therapyFrequencyId);
        therapyMedication.setId(therapyFrequencyId);

        TherapyMedication found = therapyMedicationDAO.findActive(therapyFrequencyId);
        assertNotNull(found);
        assertEquals(found.getDescription(), STR_SAMPLE_1);

        therapyMedication.setTimesPer("WEEK");
        int update = therapyMedicationDAO.update(therapyMedication);
        assertNotNull(update);

        TherapyMedication updated = therapyMedicationDAO.findActive(therapyFrequencyId);
        assertNotNull(updated);
        assertEquals(updated.getTimesPer(), "WEEK");

        long deleted = therapyMedicationDAO.remove(therapyMedication.getId(), CREATED_BY);
        assertNotNull(deleted);

        TherapyMedication delTerapyFrequenct = therapyMedicationDAO.findActive(therapyMedication.getId());
        assertNull(delTerapyFrequenct);

        h.rollback();
        h.close();
    }
}
