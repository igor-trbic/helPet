package helPet.resources;

import helPet.auth.AuthMe;
import helPet.dto.AppointmentDTO;
import helPet.entity.Appointment;
import helPet.entity.User;
import helPet.managers.AppointmentManager;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("/appointments")
public class AppointmentResource {
    private final AppointmentManager appointmentManager;

    public AppointmentResource(AppointmentManager appointmentManager) {
        this.appointmentManager = appointmentManager;
    }

    @GET
    public Response get(@AuthMe User user) {
        try {
            List<AppointmentDTO> appointments = appointmentManager.get(user);
            if (appointments != null) {
                return Response.ok(appointments).build();
            }
            return Response.ok("Cannot retrieve appointments").build();
        } catch (Exception ex) {
            return Response.ok("Cannot retrieve appointments").build();
        }
    }

    @GET
    @Path("/business/{businessId}")
    public Response getForBusiness(@AuthMe User user,
                                   @PathParam("businessId") Long businessId) {
        try {
            List<AppointmentDTO> appointments = appointmentManager.getForBusiness(businessId, user);
            if (appointments != null) {
                return Response.ok(appointments).build();
            }
            return Response.ok("Cannot retrieve appointments").build();
        } catch (Exception ex) {
            return Response.ok("Cannot retrieve appointments").build();
        }
    }

    @POST
    public Response create(@AuthMe User user,
                           Appointment appointment) {
        try {
            Appointment appointment1 = appointmentManager.create(appointment, user);
            if (appointment1 != null) {
                return Response.ok(appointment1).build();
            }
            return Response.ok("Cannot create appointments").build();
        } catch (Exception ex) {
            return Response.ok("Cannot create appointments").build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response update(@AuthMe User user,
                           @PathParam("id") Long id,
                           Appointment appointment) {
        appointment.setId(id);
        try {
            Appointment updateBusinessRoleType = appointmentManager.update(appointment, user);
            if (updateBusinessRoleType != null) {
                return Response.ok(updateBusinessRoleType).build();
            }
            return Response.ok("Cannot update appointments").build();
        } catch (Exception ex) {
            return Response.ok("Cannot update appointments").build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response remove(@AuthMe User user,
                           @PathParam("id") Long id) {
        try {
            Boolean removed = appointmentManager.remove(id, user);
            if (removed) {
                return Response.ok(true).build();
            }
            return Response.ok(false).build();
        } catch (Exception ex) {
            return Response.ok(false).build();
        }
    }
}
