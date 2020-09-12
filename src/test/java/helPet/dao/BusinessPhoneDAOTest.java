package helPet.dao;

import helPet.entity.Business;
import helPet.entity.BusinessPhone;
import helPet.entity.Phone;
import helPet.entity.User;
import helPet.entity.util.EntityStatus;
import helPet.jdbi.BaseTest;
import org.jdbi.v3.core.Handle;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertNotNull;

public class BusinessPhoneDAOTest extends BaseTest {
    @Test
    public void testUserPhone() {
        Handle h = dbi.open();
        PhoneDAO phoneDAO = h.attach(PhoneDAO.class);
        UserDAO userDAO = h.attach(UserDAO.class);
        BusinessDAO businessDAO = h.attach(BusinessDAO.class);
        BusinessPhoneDAO businessPhoneDAO = h.attach(BusinessPhoneDAO.class);

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
        user.setId(userId);
        long phoneId = phoneDAO.insert(phone);

        Business business = new Business();
        business.setBusinessOwnerId(user.getId());
        business.setBusinessName(STR_SAMPLE_1);
        business.setTaxId(STR_SAMPLE_2);
        business.setNationalId(STR_SAMPLE_3);
        business.setStatus(EntityStatus.ACTIVE);
        business.setCreatedBy(CREATED_BY);

        long businessId = businessDAO.insert(business);

        BusinessPhone businessPhone =  new BusinessPhone();
        businessPhone.setBusinessId(businessId);
        businessPhone.setPhoneId(phoneId);

        long businessPhoneId = businessPhoneDAO.insert(businessPhone);
        assertNotNull(businessPhoneId);

        h.rollback();
        h.close();
    }
}
