package helPet.resources;

import helPet.auth.AuthMe;
import helPet.entity.BusinessRoleType;
import helPet.entity.User;
import helPet.managers.BusinessRoleTypeManager;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/business-roles")
public class BusinessRoleTypeResource {
    private final BusinessRoleTypeManager businessRoleTypeManager;

    public BusinessRoleTypeResource(BusinessRoleTypeManager businessRoleTypeManager) {
        this.businessRoleTypeManager = businessRoleTypeManager;
    }

    @GET
    @Path("/{businessId}")
    public Response get(@AuthMe User user,
                        @PathParam("businessId") Long businessId) {
        try {
            List<BusinessRoleType> pets = businessRoleTypeManager.get(businessId, user);
            if (pets != null) {
                return Response.ok(pets).build();
            }
            return Response.ok("Cannot retrieve role types").build();
        } catch (Exception ex) {
            return Response.ok("Cannot retrieve role types").build();
        }
    }

    @POST
    @Path("/{businessId}")
    public Response create(@AuthMe User user,
                           @PathParam("businessId") Long businessId,
                           BusinessRoleType roleType) {
        roleType.setBusinessId(businessId);
        try {
            BusinessRoleType newBusinessRoleType = businessRoleTypeManager.create(roleType, user);
            if (newBusinessRoleType != null) {
                return Response.ok(newBusinessRoleType).build();
            }
            return Response.ok("Cannot create role type").build();
        } catch (Exception ex) {
            return Response.ok("Cannot create role type").build();
        }
    }

    @PUT
    @Path("/{businessId}/type/{id}")
    public Response update(@AuthMe User user,
                           @PathParam("businessId") Long businessId,
                           @PathParam("id") Long id,
                           BusinessRoleType roleType) {
        roleType.setId(id);
        roleType.setBusinessId(businessId);
        try {
            BusinessRoleType updateBusinessRoleType = businessRoleTypeManager.update(roleType, user);
            if (updateBusinessRoleType != null) {
                return Response.ok(updateBusinessRoleType).build();
            }
            return Response.ok("Cannot update role type").build();
        } catch (Exception ex) {
            return Response.ok("Cannot update role type").build();
        }
    }

    @DELETE
    @Path("/{businessId}/type/{id}")
    public Response remove(@AuthMe User user,
                           @PathParam("businessId") Long businessId,
                           @PathParam("id") Long id) {
        try {
            Boolean removed = businessRoleTypeManager.remove(businessId, id, user);
            if (removed) {
                return Response.ok(true).build();
            }
            return Response.ok(false).build();
        } catch (Exception ex) {
            return Response.ok(false).build();
        }
    }
}
