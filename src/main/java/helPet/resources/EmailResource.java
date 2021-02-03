package helPet.resources;

import helPet.auth.AuthMe;
import helPet.entity.Email;
import helPet.entity.User;
import helPet.managers.EmailManager;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/emails")
public class EmailResource {

    private final EmailManager emailManager;

    public EmailResource(EmailManager emailManager) {
        this.emailManager = emailManager;
    }

    @GET
    public Response getEmails(@AuthMe User user) {
        try {
            List<Email> pets = emailManager.getEmails(user);
            if (pets != null) {
                return Response.ok(pets).build();
            }
            return Response.ok("Cannot retrieve emails").build();
        } catch (Exception ex) {
            return Response.ok("Cannot retrieve emails").build();
        }
    }

    @POST
    public Response createEmail(@AuthMe User user,
                                Email email) {
        try {
            Email newEmail = emailManager.createEmail(email, user);
            if (newEmail != null) {
                return Response.ok(newEmail).build();
            }
            return Response.ok("Cannot create email").build();
        } catch (Exception ex) {
            return Response.ok("Cannot create email").build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateEmail(@AuthMe User user,
                                @PathParam("id") Long id,
                                Email email) {
        email.setId(id);
        try {
            Email updateEmail = emailManager.updateEmail(email, user);
            if (updateEmail != null) {
                return Response.ok(updateEmail).build();
            }
            return Response.ok("Cannot update email").build();
        } catch (Exception ex) {
            return Response.ok("Cannot update email").build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response removeEmail(@AuthMe User user,
                                @PathParam("id") Long id) {
        try {
            Boolean removed = emailManager.removeEmail(id, user);
            if (removed) {
                return Response.ok(true).build();
            }
            return Response.ok(false).build();
        } catch (Exception ex) {
            return Response.ok(false).build();
        }
    }
}
