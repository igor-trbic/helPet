package helPet.dao;

import helPet.entity.Address;
import helPet.entity.User;
import helPet.entity.UserAddress;
import helPet.entity.util.EntityStatus;
import helPet.jdbi.BaseTest;
import org.jdbi.v3.core.Handle;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertNotNull;

public class UserAddressDAOTest extends BaseTest {
    @Test
    public void createUser() {
        Handle h = dbi.open();
        AddressDAO addressDAO = h.attach(AddressDAO.class);
        UserDAO userDAO = h.attach(UserDAO.class);
        UserAddressDAO userAddressDAO = h.attach(UserAddressDAO.class);

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
        long addressId = addressDAO.insert(address);

        UserAddress userAddress =  new UserAddress();
        userAddress.setUserId(userId);
        userAddress.setAddressId(addressId);

        long userAddressId = userAddressDAO.insert(userAddress);
        assertNotNull(userAddressId);

        h.rollback();
        h.close();
    }
}
