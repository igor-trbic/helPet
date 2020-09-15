package helPet.dao;

import helPet.entity.Appointment;
import helPet.entity.Business;
import helPet.entity.Pet;
import helPet.entity.User;
import helPet.entity.util.EntityStatus;
import helPet.jdbi.BaseTest;
import org.jdbi.v3.core.Handle;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class AppointmentDAOTest extends BaseTest {
    @Test
    public void testAppointment() {
        Handle h = dbi.open();
        PetDAO petDAO = h.attach(PetDAO.class);
        UserDAO userDAO = h.attach(UserDAO.class);
        BusinessDAO businessDAO = h.attach(BusinessDAO.class);
        AppointmentDAO appointmentDAO = h.attach(AppointmentDAO.class);

        User user = new User();
        user.setUsername(STR_SAMPLE_1);
        user.setFirstName(STR_SAMPLE_2);
        user.setLastName(STR_SAMPLE_3);
        user.setPassword("f27f7q%$@#$%f/afasdfaf");
        user.setDateOfBirth(new Date());
        user.setCreatedOn(new Date());
        user.setCreatedBy(CREATED_BY);
        user.setStatus(EntityStatus.ACTIVE);

        Pet pet = new Pet();
        pet.setName(STR_SAMPLE_1);
        pet.setNote(STR_SAMPLE_2);
        pet.setDateOfBirth(new Date());
        pet.setStatus(EntityStatus.ACTIVE);
        pet.setCreatedBy(CREATED_BY);

        h.begin();

        long userId = userDAO.insert(user);
        user.setId(userId);
        long petId = petDAO.insert(pet);

        Business business = new Business();
        business.setBusinessOwnerId(user.getId());
        business.setBusinessName(STR_SAMPLE_1);
        business.setTaxId(STR_SAMPLE_2);
        business.setNationalId(STR_SAMPLE_3);
        business.setStatus(EntityStatus.ACTIVE);
        business.setCreatedBy(CREATED_BY);

        long businessId = businessDAO.insert(business);

        Appointment appointment =  new Appointment();
        appointment.setBusinessId(businessId);
        appointment.setPetId(petId);
        appointment.setUserId(userId);
        appointment.setNote(STR_SAMPLE_1);
        appointment.setDate(new Date());
        appointment.setCreatedBy(CREATED_BY);
        appointment.setStatus(EntityStatus.ACTIVE);

        long appointmentId = appointmentDAO.insert(appointment);
        assertNotNull(appointmentId);
        appointment.setId(appointmentId);

        Appointment found = appointmentDAO.findActive(appointment.getId());
        assertNotNull(found);
        assertEquals(found.getNote(), STR_SAMPLE_1);

        appointment.setNote(STR_SAMPLE_3);
        appointment.setUpdatedBy(CREATED_BY);

        int updated = appointmentDAO.update(appointment);
        assertNotNull(updated);
        assertEquals(appointment.getNote(), STR_SAMPLE_3);

        int del = appointmentDAO.remove(appointment.getId(), CREATED_BY);
        assertNotNull(del);

        Appointment deleted = appointmentDAO.findActive(appointment.getId());
        assertNull(deleted);

        h.rollback();
        h.close();
    }
}
