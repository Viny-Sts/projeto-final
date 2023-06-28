package br.edu.ifg.luziania.controller;

import br.edu.ifg.luziania.model.bo.ActivityBO;
import br.edu.ifg.luziania.model.dto.ActivityDTO;
import br.edu.ifg.luziania.model.dto.ActivityReturnDTO;
import br.edu.ifg.luziania.model.dto.UserReturnDTO;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("")
public class ActivityController {
    @Inject
    ActivityBO activityBO;

    private final Template activity;

    public ActivityController(Template activity) {
        this.activity = activity;
    }

    @GET
    @Path("/activity")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance getActivity() {
        return activity.instance();
    }

    @POST
    @Path("/activities")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response setActivity(ActivityDTO activityDTO) {
        ActivityReturnDTO activityReturnDTO = activityBO.save(activityDTO);

        return Response.status(activityReturnDTO.getStatus()).entity(activityReturnDTO).build();
    }
}
