package helPet.resources;

import helPet.entity.Sample;
import helPet.managers.SampleManager;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/sample")
public class SampleResource {

    private final SampleManager sampleManager;

    public SampleResource(SampleManager sampleManager) {
        this.sampleManager = sampleManager;
    }

    @POST
    public Response register(Sample sample) {
        try {
            Boolean returned = sampleManager.sampleMe(sample);
            return Response.ok(returned).build();
        } catch (Exception ex) {
            return Response.serverError().build();
        }
    }
}
