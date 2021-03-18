package helPet.resources;

import helPet.auth.AuthMe;
import helPet.entity.PetAttribute;
import helPet.entity.User;
import helPet.managers.PetAttributeManager;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/pet-attributes")
public class PetAttributeResource {

    private final PetAttributeManager petAttributeManager;

    public PetAttributeResource(PetAttributeManager petAttributeManager) {
        this.petAttributeManager= petAttributeManager;
    }

    @GET
    @Path("/{petId}")
    public Response getPetAttributes(@AuthMe User user,
                                     @PathParam("petId") Long petId) {
        try {
            List<PetAttribute> petAttributes = petAttributeManager.getPetAttributes(petId, user);
            if (petAttributes != null) {
                return Response.ok(petAttributes).build();
            }
            return Response.ok("Cannot retrieve pets").build();
        } catch (Exception ex) {
            return Response.ok("Cannot retrieve pets").build();
        }
    }

//    @GET
//    @Path("/{petId}/attribute/{attrId}")
//    public Response getPet(@AuthMe User user,
//                           @PathParam("petId") Long petId,
//                           @PathParam("attrId") Long attrId) {
//        try {
//            Pet pet = petAttributeManager.getPetAttribute(user, petId, attrId);
//            if (pet != null) {
//                return Response.ok(pet).build();
//            }
//            return Response.ok("Cannot retrieve pets").build();
//        } catch (Exception ex) {
//            return Response.ok("Cannot retrieve pets").build();
//        }
//    }

    @POST
    @Path("/{petId}")
    public Response createPetAttribute(@AuthMe User user,
                                       @PathParam("petId") Long petId,
                                       PetAttribute petAttribute) {
        petAttribute.setPetId(petId);
        try {
            PetAttribute petAttr = petAttributeManager.createPetAttribute(petAttribute, user);
            if (petAttr != null) {
                return Response.ok(petAttr).build();
            }
            return Response.ok("Cannot create pet").build();
        } catch (Exception ex) {
            return Response.ok("Cannot create pet").build();
        }
    }

    @PUT
    @Path("/{petId}/attribute/{attrId}")
    public Response updatePetAttribute(@AuthMe User user,
                                       @PathParam("petId") Long petId,
                                       @PathParam("attrId") Long attrId,
                                       PetAttribute petAttribute) {
        petAttribute.setId(attrId);
        petAttribute.setPetId(petId);
        try {
            PetAttribute updatePetAttribute = petAttributeManager.updatePetAttribute(petAttribute, user);
            if (updatePetAttribute != null) {
                return Response.ok(updatePetAttribute).build();
            }
            return Response.ok("Cannot update pet").build();
        } catch (Exception ex) {
            return Response.ok("Cannot update pet").build();
        }
    }

    @DELETE
    @Path("/{petId}/attribute/{attrId}")
    public Response removePetAttribute(@AuthMe User user,
                                       @PathParam("petId") Long petId,
                                       @PathParam("attrId") Long attrId) {
        try {
            Boolean removed = petAttributeManager.removePetAttribute(petId, attrId, user);
            if (removed) {
                return Response.ok(true).build();
            }
            return Response.ok(false).build();
        } catch (Exception ex) {
            return Response.ok(false).build();
        }
    }
}
