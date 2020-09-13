package helPet.dao;

import helPet.entity.Address;
import helPet.entity.Business;
import helPet.entity.BusinessAddress;
import helPet.entity.BusinessPhone;
import helPet.entity.Phone;
import helPet.entity.User;
import helPet.entity.util.EntityStatus;
import helPet.jdbi.BaseTest;
import org.jdbi.v3.core.Handle;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertNotNull;

public class BusinessAddressDAOTest extends BaseTest {
    @Test
    public void testUserPhone() {
        Handle h = dbi.open();
        AddressDAO phoneDAO = h.attach(AddressDAO.class);
        UserDAO userDAO = h.attach(UserDAO.class);
        BusinessDAO businessDAO = h.attach(BusinessDAO.class);
        BusinessAddressDAO businessAddressDAO = h.attach(BusinessAddressDAO.class);

        Address address = new Address();
        address.setStreetName(STR_SAMPLE_1);
        address.setHouseNumber("123");
        address.setAddressType("PERSONAL");
        address.setPostalCode(STR_SAMPLE_2);
        address.setStatus(EntityStatus.ACTIVE);
        address.setCreatedBy(CREATED_BY);

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
        long addressId = phoneDAO.insert(address);

        Business business = new Business();
        business.setBusinessOwnerId(user.getId());
        business.setBusinessName(STR_SAMPLE_1);
        business.setTaxId(STR_SAMPLE_2);
        business.setNationalId(STR_SAMPLE_3);
        business.setStatus(EntityStatus.ACTIVE);
        business.setCreatedBy(CREATED_BY);

        long businessId = businessDAO.insert(business);

        BusinessAddress businessAddress =  new BusinessAddress();
        businessAddress.setBusinessId(businessId);
        businessAddress.setAddressId(addressId);

        long businessPhoneId = businessAddressDAO.insert(businessAddress);
        assertNotNull(businessPhoneId);

        h.rollback();
        h.close();
    }
}
