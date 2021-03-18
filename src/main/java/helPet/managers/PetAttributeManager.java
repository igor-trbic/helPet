package helPet.managers;

import helPet.dao.PetAttributeDAO;
import helPet.dao.PetDAO;
import helPet.dao.PetOwnerDAO;
import helPet.entity.Pet;
import helPet.entity.PetAttribute;
import helPet.entity.PetOwner;
import helPet.entity.User;
import helPet.entity.util.EntityStatus;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class PetAttributeManager {

    private static final Logger LOG = LoggerFactory.getLogger(RegistrationManager.class);
    private Jdbi dbi;

    public PetAttributeManager(Jdbi dbi) {
        this.dbi=dbi;
    }

    public List<PetAttribute> getPetAttributes(Long petId, User user) {
        Handle h = dbi.open();
        List<PetAttribute> petAttributes = new ArrayList<>();
        try {
            h.begin();
            PetAttributeDAO petAttributeDAO = h.attach(PetAttributeDAO.class);

            // TODO: Maybe include userId as well???
            petAttributes = petAttributeDAO.findActiveByPetId(petId);

            h.commit();
        } catch (Exception ex) {
            petAttributes = null;
            LOG.error(ex.getMessage());
            h.rollback();
        } finally {
            h.close();
        }
        return petAttributes;
    }

    public PetAttribute createPetAttribute(PetAttribute petAttribute, User user) {
        Handle h = dbi.open();
        try {
            h.begin();
            PetDAO petDAO = h.attach(PetDAO.class);
            PetAttributeDAO petAttributeDAO = h.attach(PetAttributeDAO.class);

            Pet pet = petDAO.findByUserIdAndPetId(user.getId(), petAttribute.getPetId());
            if (pet == null) {
                // TODO: implement client error
                throw new Exception("Cannot find pet");
            }

            petAttribute.setCreatedBy(user.getUsername());
            petAttribute.setStatus(EntityStatus.ACTIVE);
            long petAttrId = petAttributeDAO.insert(petAttribute);
            if (petAttrId == 0) {
                throw new Exception("Cannot insert pet attribute");
            }
            petAttribute.setId(petAttrId);

            h.commit();
        } catch (Exception ex) {
            petAttribute = null;
            LOG.error(ex.getMessage());
            h.rollback();
        } finally {
            h.close();
        }
        return petAttribute;
    }

    public PetAttribute updatePetAttribute(PetAttribute petAttribute, User user) {
        Handle h = dbi.open();
        try {
            h.begin();
            // TODO: Maybe check if pet exists at all
//            PetDAO petDAO = h.attach(PetDAO.class);
            PetAttributeDAO petAttributeDAO = h.attach(PetAttributeDAO.class);

            petAttribute.setUpdatedBy(user.getUsername());
            long updatedFlag = petAttributeDAO.update(petAttribute);
            if (updatedFlag == 0) {
                // TODO: implement client error
                throw new Exception("Cannot insert pet");
            }

            h.commit();
        } catch (Exception ex) {
            petAttribute = null;
            LOG.error(ex.getMessage());
            h.rollback();
        } finally {
            h.close();
        }
        return petAttribute;
    }

    public Boolean removePetAttribute(Long petId, Long attrId, User user) {
        Boolean success = false;
        Handle h = dbi.open();
        try {
            h.begin();
            // TODO: Maybe check if pet exists at all
//            PetDAO petDAO = h.attach(PetDAO.class);
            PetAttributeDAO petAttributeDAO = h.attach(PetAttributeDAO.class);

            long updatedFlag = petAttributeDAO.remove(attrId, user.getUsername());
            if (updatedFlag == 0) {
                // TODO: implement client error
                throw new Exception("Cannot insert pet");
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
}
