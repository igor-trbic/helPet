package helPet.dao;

import helPet.entity.Address;
import helPet.entity.Phone;
import helPet.entity.User;
import helPet.entity.UserAddress;
import helPet.entity.UserPhone;
import helPet.entity.util.EntityStatus;
import helPet.jdbi.BaseTest;
import org.jdbi.v3.core.Handle;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertNotNull;

public class UserPhoneDAOTest extends BaseTest {
    @Test
    public void testUserPhone() {
        Handle h = dbi.open();
        PhoneDAO phoneDAO = h.attach(PhoneDAO.class);
        UserDAO userDAO = h.attach(UserDAO.class);
        UserPhoneDAO userPhoneDAO = h.attach(UserPhoneDAO.class);

        Phone phone = new Phone();
        phone.setPhoneNumber(STR_SAMPLE_1);
        phone.setPhoneType(STR_SAMPLE_2);
        phone.setStatus(EntityStatus.ACTIVE);
        phone.setCreatedBy(CREATED_BY);

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
        long phoneId = phoneDAO.insert(phone);

        UserPhone userPhone =  new UserPhone();
        userPhone.setUserId(userId);
        userPhone.setPhoneId(phoneId);

        long userAddressId = userPhoneDAO.insert(userPhone);
        assertNotNull(userAddressId);

        h.rollback();
        h.close();
    }
}
