package helPet.resources;

import helPet.auth.AuthMe;
import helPet.entity.Address;
import helPet.entity.User;
import helPet.managers.AddressManager;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/addresses")
public class AddressResource {
    private final AddressManager addressManager;

    public AddressResource(AddressManager addressManager) {
        this.addressManager = addressManager;
    }

    @GET
    public Response getAddresses(@AuthMe User user) {
        try {
            List<Address> pets = addressManager.getAddresses(user);
            if (pets != null) {
                return Response.ok(pets).build();
            }
            return Response.ok("Cannot retrieve addresses").build();
        } catch (Exception ex) {
            return Response.ok("Cannot retrieve addresses").build();
        }
    }

    @POST
    public Response createAddress(@AuthMe User user,
                                  Address address) {
        try {
            Address newAddress = addressManager.createAddress(address, user);
            if (newAddress != null) {
                return Response.ok(newAddress).build();
            }
            return Response.ok("Cannot create address").build();
        } catch (Exception ex) {
            return Response.ok("Cannot create address").build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateAddress(@AuthMe User user,
                                  @PathParam("id") Long id,
                                  Address address) {
        address.setId(id);
        try {
            Address updateAddress = addressManager.updateAddress(address, user);
            if (updateAddress != null) {
                return Response.ok(updateAddress).build();
            }
            return Response.ok("Cannot update address").build();
        } catch (Exception ex) {
            return Response.ok("Cannot update address").build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response removeAddress(@AuthMe User user,
                                  @PathParam("id") Long id) {
        try {
            Boolean removed = addressManager.removeAddress(id, user);
            if (removed) {
                return Response.ok(true).build();
            }
            return Response.ok(false).build();
        } catch (Exception ex) {
            return Response.ok(false).build();
        }
    }
}
