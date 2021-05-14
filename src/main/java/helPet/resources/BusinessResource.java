package helPet.resources;

import helPet.auth.AuthMe;
import helPet.entity.Business;
import helPet.entity.User;
import helPet.managers.BusinessManager;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/business")
public class BusinessResource {
    private final BusinessManager businessManager;

    public BusinessResource(BusinessManager businessStaffManager) {
        this.businessManager = businessStaffManager;
    }

    @GET
    public Response get(@AuthMe User user) {
        try {
            List<Business> businesses = businessManager.get();
            if (businesses != null) {
                return Response.ok(businesses).build();
            }
            return Response.ok("Cannot retrieve businesses").build();
        } catch (Exception ex) {
            return Response.ok("Cannot retrieve businesses").build();
        }
    }
}
