package helPet.resources;

import helPet.auth.AuthMe;
import helPet.entity.Pet;
import helPet.entity.User;
import helPet.managers.PetManager;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/pets")
public class PetResource {

    private final PetManager petManager;

    public PetResource(PetManager petManager) {
        this.petManager = petManager;
    }

    @GET
    public Response getPets(@AuthMe User user) {
        try {
            List<Pet> pets = petManager.getPets(user);
            if (pets != null) {
                return Response.ok(pets).build();
            }
            return Response.ok("Cannot retrieve pets").build();
        } catch (Exception ex) {
            return Response.ok("Cannot retrieve pets").build();
        }
    }

    @GET
    @Path("/{id}")
    public Response getPet(@AuthMe User user,
                           @PathParam("id") Long id) {
        try {
            Pet pet = petManager.getPet(user, id);
            if (pet != null) {
                return Response.ok(pet).build();
            }
            return Response.ok("Cannot retrieve pets").build();
        } catch (Exception ex) {
            return Response.ok("Cannot retrieve pets").build();
        }
    }

    @POST
    public Response createPet(@AuthMe User user,
                              Pet pet) {
        try {
            Pet newPet = petManager.createPet(pet, user);
            if (newPet != null) {
                return Response.ok(newPet).build();
            }
            return Response.ok("Cannot create pet").build();
        } catch (Exception ex) {
            return Response.ok("Cannot create pet").build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updatePet(@AuthMe User user,
                              @PathParam("id") Long id,
                              Pet pet) {
        pet.setId(id);
        try {
            Pet updatePet = petManager.updatePet(pet, user);
            if (updatePet != null) {
                return Response.ok(updatePet).build();
            }
            return Response.ok("Cannot update pet").build();
        } catch (Exception ex) {
            return Response.ok("Cannot update pet").build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response removePet(@AuthMe User user,
                              @PathParam("id") Long id) {
        try {
            Boolean removed = petManager.removePet(id, user);
            if (removed) {
                return Response.ok(true).build();
            }
            return Response.ok(false).build();
        } catch (Exception ex) {
            return Response.ok(false).build();
        }
    }
}
