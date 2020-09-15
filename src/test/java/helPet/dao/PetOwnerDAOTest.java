package helPet.dao;

import helPet.entity.Pet;
import helPet.entity.PetOwner;
import helPet.entity.User;
import helPet.entity.util.EntityStatus;
import helPet.jdbi.BaseTest;
import org.jdbi.v3.core.Handle;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertNotNull;

public class PetOwnerDAOTest extends BaseTest {
    @Test
    public void testUserAddress() {
        Handle h = dbi.open();
        PetDAO petDAO = h.attach(PetDAO.class);
        UserDAO userDAO = h.attach(UserDAO.class);
        PetOwnerDAO petOwnerDAO = h.attach(PetOwnerDAO.class);

        Pet pet = new Pet();
        pet.setName(STR_SAMPLE_1);
        pet.setNote(STR_SAMPLE_2);
        pet.setDateOfBirth(new Date());
        pet.setStatus(EntityStatus.ACTIVE);
        pet.setCreatedBy(CREATED_BY);

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
        long petId = petDAO.insert(pet);

        PetOwner petOwner =  new PetOwner();
        petOwner.setUserId(userId);
        petOwner.setPetId(petId);

        long userAddressId = petOwnerDAO.insert(petOwner);
        assertNotNull(userAddressId);

        h.rollback();
        h.close();
    }
}
