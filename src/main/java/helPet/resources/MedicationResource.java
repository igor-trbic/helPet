package helPet.resources;

import helPet.entity.Medication;
import helPet.entity.User;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/medications")
@Produces(MediaType.APPLICATION_JSON)
public class MedicationResource<Path> {
    @POST
    public Response createMedication(User user,
                                     Medication medication) {
        return Response.ok("HELLO!").build();
    }
}
