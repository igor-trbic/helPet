package helPet.resources;

import helPet.auth.AuthMe;
import helPet.dto.BusinessStaffDTO;
import helPet.entity.User;
import helPet.managers.BusinessStaffManager;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/staff")
public class BusinessStaffResource {
    private final BusinessStaffManager businessStaffManager;

    public BusinessStaffResource(BusinessStaffManager businessStaffManager) {
        this.businessStaffManager = businessStaffManager;
    }

    @POST
    @Path("/{businessId}/invite")
    public Response invite(@AuthMe User user,
                           @PathParam("businessId") Long businessId,
                           BusinessStaffDTO businessStaffDTO) {
        businessStaffDTO.setBusinessId(businessId);
        try {
            Boolean invited = businessStaffManager.invite(businessStaffDTO, user);
            if (invited) {
                return Response.ok("Email sent").build();
            }
            return Response.ok(businessStaffDTO).build();
        } catch (Exception ex) {
            return Response.ok("Cannot create staff").build();
        }
    }

    @GET
    @Path("/{businessId}")
    public Response get(@AuthMe User user,
                        @PathParam("businessId") Long businessId) {
        try {
            List<BusinessStaffDTO> pets = businessStaffManager.get(businessId, user);
            if (pets != null) {
                return Response.ok(pets).build();
            }
            return Response.ok("Cannot retrieve staff").build();
        } catch (Exception ex) {
            return Response.ok("Cannot retrieve staff").build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response update(@AuthMe User user,
                           @PathParam("id") Long id,
                           BusinessStaffDTO businessStaffDTO) {
        businessStaffDTO.setId(id);
        try {
            BusinessStaffDTO businessStaff = businessStaffManager.update(businessStaffDTO, user);
            if (businessStaff != null) {
                return Response.ok(businessStaff).build();
            }
            return Response.ok("Cannot update staff").build();
        } catch (Exception ex) {
            return Response.ok("Cannot update staff").build();
        }
    }

//    @DELETE
//    @Path("/{id}")
//    public Response remove(@AuthMe User user,
//                           @PathParam("id") Long id) {
//        try {
//            Boolean removed = businessStaffManager.remove(id, user);
//            if (removed) {
//                return Response.ok(true).build();
//            }
//            return Response.ok(false).build();
//        } catch (Exception ex) {
//            return Response.ok(false).build();
//        }
//    }
}
