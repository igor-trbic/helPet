package helPet.dao;

import helPet.entity.Email;
import helPet.entity.User;
import helPet.entity.util.EntityStatus;
import helPet.jdbi.BaseTest;
import org.jdbi.v3.core.Handle;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class EmailDAOTest extends BaseTest {
    @Test
    public void testAddress() {
        Handle h = dbi.open();
        UserDAO userDAO = h.attach(UserDAO.class);
        EmailDAO emailDAO = h.attach(EmailDAO.class);

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

        Email email = new Email();
        email.setEmailAddress(STR_SAMPLE_1);
        email.setEmailType("PERSONAL");
        email.setIsPrimary(true);
        email.setUserId(user.getId());
        email.setStatus(EntityStatus.ACTIVE);
        email.setCreatedBy(CREATED_BY);

        h.begin();

        long id = emailDAO.insert(email);
        email.setId(id);

        Email found = emailDAO.findActive(email.getId());
        assertNotNull(found);
        assertEquals(found.getEmailAddress(), STR_SAMPLE_1);

        email.setEmailAddress(STR_SAMPLE_3);
        email.setUpdatedBy(CREATED_BY);

        int updated = emailDAO.update(email);
        assertNotNull(updated);
        assertEquals(email.getEmailAddress(), STR_SAMPLE_3);

        int del = emailDAO.remove(email.getId(), CREATED_BY);
        assertNotNull(del);

        Email deleted = emailDAO.findActive(email.getId());
        assertNull(deleted);

        h.rollback();
        h.close();
    }
}
