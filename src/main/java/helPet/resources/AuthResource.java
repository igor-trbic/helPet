package helPet.resources;

import helPet.dto.UserDTO;
import helPet.entity.AuthReq;
import helPet.entity.User;
import helPet.managers.AuthManager;
import helPet.managers.HelPetSecurityManager;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
public class AuthResource {


    private final AuthManager authManager;
    private final HelPetSecurityManager helPetSecurityManager;

    public AuthResource(AuthManager authManager, HelPetSecurityManager helPetSecurityManager) {
        this.authManager = authManager;
        this.helPetSecurityManager = helPetSecurityManager;
    }

    @POST
    public Response login(UserDTO userDTO,
                          @Context HttpServletRequest context) {
        try {

            userDTO = authManager.validate(userDTO);
            if (userDTO != null) {
                final int HOUR = 60 * 60;
                final int DAY = HOUR * 24;
                int duration = DAY;
//            if (StringUtils.isNotBlank(authReq.getRemember()) && "Y".equals(authReq.getRemember())) {
//                duration = DAY * 5;
//            }
                String token = authManager.generateToken(userDTO);
                if (token == null) {
                    return Response.serverError().build();
                }
                userDTO.setToken(token);
                return Response.ok(userDTO).header("helpet-token", token).build();
            } else {

            }
        } catch (Exception ex) {
            return Response.serverError().build();
        }
        return Response.serverError().build();
    }
}
