package helPet.dao;

import helPet.entity.User;
import helPet.entity.util.EntityStatus;
import helPet.jdbi.BaseTest;
import org.jdbi.v3.core.Handle;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class UserDAOTest extends BaseTest {
    @Test
    public void createUser() {
        Handle h = dbi.open();
        UserDAO userDAO = h.attach(UserDAO.class);

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

        long id = userDAO.insert(user);
        user.setId(id);

        User found = userDAO.find(user.getId());
        assertNotNull(found);
        assertEquals(found.getFirstName(), STR_SAMPLE_2);

        user.setFirstName(STR_SAMPLE_3);
        user.setUpdatedBy(CREATED_BY);

        int updated = userDAO.update(user);
        assertNotNull(updated);
        assertEquals(user.getFirstName(), STR_SAMPLE_3);

        int del = userDAO.remove(user.getId(), CREATED_BY);
        assertNotNull(del);

        User deleted = userDAO.find(user.getId());
        assertNull(deleted);

        h.rollback();
        h.close();
    }
}
