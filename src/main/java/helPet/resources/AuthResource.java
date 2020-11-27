package helPet.resources;

import helPet.entity.User;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
public class AuthResource {
    @POST
    public Response register(@QueryParam("lang") String lang,
                             User user) {
        return Response.ok("HELLO!").build();
    }
}
