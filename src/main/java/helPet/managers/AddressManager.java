package helPet.managers;

import helPet.dao.AddressDAO;
import helPet.dao.BusinessAddressDAO;
import helPet.dao.UserAddressDAO;
import helPet.dto.UserDTO;
import helPet.entity.Address;
import helPet.entity.BusinessAddress;
import helPet.entity.User;
import helPet.entity.UserAddress;
import helPet.entity.util.EntityStatus;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class AddressManager {

    private static final Logger LOG = LoggerFactory.getLogger(RegistrationManager.class);
    private Jdbi dbi;

    public AddressManager(Jdbi dbi) {
        this.dbi=dbi;
    }

    public Address createAddress(Address address, User user) throws Exception {
        Handle h = dbi.open();
        try {
            h.begin();
            AddressDAO addressDAO = h.attach(AddressDAO.class);
            UserAddressDAO userAddressDAO = h.attach(UserAddressDAO.class);

            address.setCreatedBy(user.getUsername());
            address.setStatus(EntityStatus.ACTIVE);
            long inserted = addressDAO.insert(address);
            if (inserted == 0) {
                // TODO: implement client error
                throw new Exception("Cannot insert address");
            }
            address.setId(inserted);

            UserAddress userAddress = new UserAddress();
            userAddress.setAddressId(address.getId());
            userAddress.setUserId(user.getId());
            long insertedOwner = userAddressDAO.insert(userAddress);
            if (insertedOwner == 0) {
                // TODO: implement client error
                throw new Exception("Cannot insert address");
            }

            h.commit();
        } catch (Exception ex) {
            address = null;
            LOG.error(ex.getMessage());
            h.rollback();
        } finally {
            h.close();
        }
        return address;
    }

    public List<Address> getAddresses(User user) {
        Handle h = dbi.open();
        List<Address> addresses = new ArrayList<>();
        try {
            h.begin();
            AddressDAO addressDAO = h.attach(AddressDAO.class);

            addresses = addressDAO.findByUserId(user.getId());

            h.commit();
        } catch (Exception ex) {
            addresses = null;
            LOG.error(ex.getMessage());
            h.rollback();
        } finally {
            h.close();
        }
        return addresses;
    }

    public Address updateAddress(Address address, User user) {
        Handle h = dbi.open();
        try {
            h.begin();
            AddressDAO addressDAO = h.attach(AddressDAO.class);

            address.setUpdatedBy(user.getUsername());
            long updatedFlag = addressDAO.update(address);
            if (updatedFlag == 0) {
                // TODO: implement client error
                throw new Exception("Cannot insert address");
            }

            h.commit();
        } catch (Exception ex) {
            address = null;
            LOG.error(ex.getMessage());
            h.rollback();
        } finally {
            h.close();
        }
        return address;
    }

    public Boolean removeAddress(Long id, User user) {
        Boolean success = false;
        Handle h = dbi.open();
        try {
            h.begin();
            AddressDAO addressDAO = h.attach(AddressDAO.class);

            long updatedFlag = addressDAO.remove(id, user.getUsername());
            if (updatedFlag == 0) {
                // TODO: implement client error
                throw new Exception("Cannot insert address");
            }
            success = true;

            h.commit();
        } catch (Exception ex) {
            success = false;
            LOG.error(ex.getMessage());
            h.rollback();
        } finally {
            h.close();
        }
        return success;
    }

    public Address createBusinessAddress(Address address, Long businessId, User user) throws Exception {
        Handle h = dbi.open();
        try {
            h.begin();
            AddressDAO addressDAO = h.attach(AddressDAO.class);
            BusinessAddressDAO businessAddressDAO = h.attach(BusinessAddressDAO.class);

            address.setCreatedBy(user.getUsername());
            address.setStatus(EntityStatus.ACTIVE);
            long inserted = addressDAO.insert(address);
            if (inserted == 0) {
                // TODO: implement client error
                throw new Exception("Cannot insert address");
            }
            address.setId(inserted);

            BusinessAddress businessAddress = new BusinessAddress();
            businessAddress.setAddressId(address.getId());
            businessAddress.setBusinessId(businessId);
            long insertedOwner = businessAddressDAO.insert(businessAddress);
            if (insertedOwner == 0) {
                // TODO: implement client error
                throw new Exception("Cannot insert business address");
            }

            h.commit();
        } catch (Exception ex) {
            address = null;
            LOG.error(ex.getMessage());
            h.rollback();
        } finally {
            h.close();
        }
        return address;
    }

    public List<Address> getBusinessAddresses(Long id, User user) {
        Handle h = dbi.open();
        List<Address> addresses = new ArrayList<>();
        try {
            h.begin();
            AddressDAO addressDAO = h.attach(AddressDAO.class);

            addresses = addressDAO.findByBusinessId(id);

            h.commit();
        } catch (Exception ex) {
            addresses = null;
            LOG.error(ex.getMessage());
            h.rollback();
        } finally {
            h.close();
        }
        return addresses;
    }
}
