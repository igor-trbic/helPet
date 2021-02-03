package helPet.resources;

import helPet.auth.AuthMe;
import helPet.entity.Phone;
import helPet.entity.User;
import helPet.managers.PhoneManager;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/phones")
public class PhoneResource {

    private final PhoneManager phoneManager;

    public PhoneResource(PhoneManager phoneManager) {
        this.phoneManager = phoneManager;
    }

    @GET
    public Response getPhones(@AuthMe User user) {
        try {
            List<Phone> pets = phoneManager.getPhones(user);
            if (pets != null) {
                return Response.ok(pets).build();
            }
            return Response.ok("Cannot retrieve phones").build();
        } catch (Exception ex) {
            return Response.ok("Cannot retrieve phones").build();
        }
    }

    @POST
    public Response createPhone(@AuthMe User user,
                                  Phone phone) {
        try {
            Phone newPhone = phoneManager.createPhone(phone, user);
            if (newPhone != null) {
                return Response.ok(newPhone).build();
            }
            return Response.ok("Cannot create phone").build();
        } catch (Exception ex) {
            return Response.ok("Cannot create phone").build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updatePhone(@AuthMe User user,
                                  @PathParam("id") Long id,
                                  Phone phone) {
        phone.setId(id);
        try {
            Phone updatePhone = phoneManager.updatePhone(phone, user);
            if (updatePhone != null) {
                return Response.ok(updatePhone).build();
            }
            return Response.ok("Cannot update phone").build();
        } catch (Exception ex) {
            return Response.ok("Cannot update phone").build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response removePhone(@AuthMe User user,
                                  @PathParam("id") Long id) {
        try {
            Boolean removed = phoneManager.removePhone(id, user);
            if (removed) {
                return Response.ok(true).build();
            }
            return Response.ok(false).build();
        } catch (Exception ex) {
            return Response.ok(false).build();
        }
    }
}
