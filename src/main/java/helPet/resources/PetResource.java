package helPet.resources;

import helPet.auth.AuthMe;
import helPet.entity.Medication;
import helPet.entity.User;
import helPet.managers.PetManager;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/pets")
public class PetResource {

    private final PetManager petManager;

    public PetResource(PetManager petManager) {
        this.petManager = petManager;
    }

    @GET
    public Response createMedication(@AuthMe User user,
                                     Medication medication) {
        System.out.println(user.toString());
        return Response.ok("HERE'S SOME PETS!!").build();
    }
}
