package helPet.managers;

import helPet.dao.PetDAO;
import helPet.dao.PetOwnerDAO;
import helPet.entity.Pet;
import helPet.entity.PetOwner;
import helPet.entity.User;
import helPet.entity.util.EntityStatus;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class PetManager {

    private static final Logger LOG = LoggerFactory.getLogger(RegistrationManager.class);
    private Jdbi dbi;

    public PetManager(Jdbi dbi) {
        this.dbi=dbi;
    }

    public Pet createPet(Pet pet, User user) throws Exception {
        Handle h = dbi.open();
        try {
            h.begin();
            PetDAO petDAO = h.attach(PetDAO.class);
            PetOwnerDAO petOwnerDAO = h.attach(PetOwnerDAO.class);

            pet.setCreatedBy(user.getUsername());
            pet.setStatus(EntityStatus.ACTIVE);
            long inserted = petDAO.insert(pet);
            if (inserted == 0) {
                // TODO: implement client error
                throw new Exception("Cannot insert pet");
            }
            pet.setId(inserted);

            PetOwner petOwner = new PetOwner();
            petOwner.setPetId(pet.getId());
            petOwner.setUserId(user.getId());
            long insertedOwner = petOwnerDAO.insert(petOwner);
            if (insertedOwner == 0) {
                // TODO: implement client error
                throw new Exception("Cannot insert pet");
            }

            h.commit();
        } catch (Exception ex) {
            pet = null;
            LOG.error(ex.getMessage());
            h.rollback();
        } finally {
            h.close();
        }
        return pet;
    }

    public List<Pet> getPets(User user) {
        Handle h = dbi.open();
        List<Pet> pets = new ArrayList<>();
        try {
            h.begin();
            PetDAO petDAO = h.attach(PetDAO.class);

            pets = petDAO.findByUserId(user.getId());

            h.commit();
        } catch (Exception ex) {
            pets = null;
            LOG.error(ex.getMessage());
            h.rollback();
        } finally {
            h.close();
        }
        return pets;
    }

    public Pet updatePet(Pet pet, User user) {
        Handle h = dbi.open();
        try {
            h.begin();
            PetDAO petDAO = h.attach(PetDAO.class);

            pet.setUpdatedBy(user.getUsername());
            long updatedFlag = petDAO.update(pet);
            if (updatedFlag == 0) {
                // TODO: implement client error
                throw new Exception("Cannot insert pet");
            }

            h.commit();
        } catch (Exception ex) {
            pet = null;
            LOG.error(ex.getMessage());
            h.rollback();
        } finally {
            h.close();
        }
        return pet;
    }

    public Boolean removePet(Long id, User user) {
        Boolean success = false;
        Handle h = dbi.open();
        try {
            h.begin();
            PetDAO petDAO = h.attach(PetDAO.class);

            long updatedFlag = petDAO.remove(id, user.getUsername());
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

    public Pet getPet(User user, Long id) throws Exception {
        Handle h = dbi.open();
        Pet pet = new Pet();
        try {
            h.begin();
            PetDAO petDAO = h.attach(PetDAO.class);

            pet = petDAO.findByUserIdAndPetId(user.getId(), id);
            if (pet == null) {
                // TODO: implement client error
                throw new Exception("Cannot retrieve pet");
            }

            h.commit();
        } catch (Exception ex) {
            pet = null;
            LOG.error(ex.getMessage());
            h.rollback();
        } finally {
            h.close();
        }
        return pet;
    }
}
