package helPet.dao;

import helPet.entity.Business;
import helPet.entity.BusinessRole;
import helPet.entity.BusinessRoleType;
import helPet.entity.BusinessStaff;
import helPet.entity.User;
import helPet.entity.util.EntityStatus;
import helPet.jdbi.BaseTest;
import org.jdbi.v3.core.Handle;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BusinessRoleDAOTest extends BaseTest {
    @Test
    public void testBusiness() {
        Handle h = dbi.open();
        UserDAO userDAO = h.attach(UserDAO.class);
        BusinessDAO businessDAO = h.attach(BusinessDAO.class);
        BusinessRoleTypeDAO businessRoleTypeDAO = h.attach(BusinessRoleTypeDAO.class);
        BusinessRoleDAO businessRoleDAO = h.attach(BusinessRoleDAO.class);
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

        long id = businessDAO.insert(business);
        business.setId(id);

        BusinessRoleType businessRoleType = new BusinessRoleType();
        businessRoleType.setBusinessRoleName(STR_SAMPLE_1);
        businessRoleType.setCreatedBy(CREATED_BY);
        businessRoleType.setStatus(EntityStatus.ACTIVE);

        long businessRoleTypeId = businessRoleTypeDAO.insert(businessRoleType);
        businessRoleType.setId(businessRoleTypeId);

        BusinessStaff businessStaff = new BusinessStaff();
        businessStaff.setBusinessId(business.getId());
        businessStaff.setUserId(user.getId());

        long businessStaffId = businessStaffDAO.insert(businessStaff);
        businessStaff.setId(businessStaffId);

        BusinessRole businessRole = new BusinessRole();
        businessRole.setBusinessStaffId(businessStaff.getId());
        businessRole.setBusinessRoleTypeId(businessRoleTypeId);
        businessRole.setCreatedBy(CREATED_BY);
        businessRole.setStatus(EntityStatus.ACTIVE);

        long businessRoleId = businessRoleDAO.insert(businessRole);
        businessRole.setId(businessRoleId);

        BusinessRole found = businessRoleDAO.findActive(businessRole.getId());
        assertNotNull(found);
        assertEquals(found.getBusinessRoleTypeId(), businessRoleType.getId());
        assertEquals(found.getBusinessStaffId(), businessStaff.getId());

        h.rollback();
        h.close();
    }
}
