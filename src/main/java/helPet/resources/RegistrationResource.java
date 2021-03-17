package helPet.resources;

import helPet.dto.BusinessRegisterDTO;
import helPet.entity.User;
import helPet.managers.RegistrationManager;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/register")
public class RegistrationResource {

    private final RegistrationManager registrationManager;

    public RegistrationResource(RegistrationManager registrationManager) {
        this.registrationManager = registrationManager;
    }

    @POST
    public Response register(User user) {
        try {
            registrationManager.register(user);
            return Response.ok("Successful registration!").build();
        } catch (Exception ex) {
            return Response.serverError().build();
        }
    }

    @POST
    @Path("/business")
    public Response registerBusiness(BusinessRegisterDTO businessRegisterDTO) {
        try {
            registrationManager.registerBusiness(businessRegisterDTO);
            return Response.ok("Successful registration!").build();
        } catch (Exception ex) {
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/{token}")
    public Response confirmRegistration(@PathParam("token") String token) {
        try {
            Boolean success = registrationManager.confirmRegistration(token);
            if (success) {
                return Response.ok("ACTIVATED!").build();
            }
            return Response.serverError().build();
        } catch (Exception ex) {
            return Response.serverError().build();
        }
    }
}
