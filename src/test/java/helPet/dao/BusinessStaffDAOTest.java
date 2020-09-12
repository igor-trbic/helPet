package helPet.dao;

import helPet.entity.Business;
import helPet.entity.BusinessStaff;
import helPet.entity.User;
import helPet.entity.util.EntityStatus;
import helPet.jdbi.BaseTest;
import org.jdbi.v3.core.Handle;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertNotNull;

public class BusinessStaffDAOTest extends BaseTest {
    @Test
    public void testUserAddress() {
        Handle h = dbi.open();
        BusinessDAO businessDAO = h.attach(BusinessDAO.class);
        UserDAO userDAO = h.attach(UserDAO.class);
        BusinessStaffDAO businessStaffDAO = h.attach(BusinessStaffDAO.class);

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

        Business business = new Business();
        business.setBusinessOwnerId(user.getId());
        business.setBusinessName(STR_SAMPLE_1);
        business.setTaxId(STR_SAMPLE_2);
        business.setNationalId(STR_SAMPLE_3);
        business.setStatus(EntityStatus.ACTIVE);
        business.setCreatedBy(CREATED_BY);

        long businessId = businessDAO.insert(business);

        BusinessStaff businessStaff = new BusinessStaff();
        businessStaff.setUserId(userId);
        businessStaff.setBusinessId(businessId);

        long userAddressId = businessStaffDAO.insert(businessStaff);
        assertNotNull(userAddressId);

        h.rollback();
        h.close();
    }
}
