package br.edu.ifg.luziania.controller;

import br.edu.ifg.luziania.model.bo.ActivityBO;
import br.edu.ifg.luziania.model.dto.ActivityReturnDTO;
import br.edu.ifg.luziania.model.util.ErrorTemplate;
import br.edu.ifg.luziania.model.util.Session;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/activity")
public class ActivityController {
    @Inject
    Session session;
    @Inject
    ActivityBO activityBO;

    private final Template activity;

    public ActivityController(Template activity) {
        this.activity = activity;
    }

    @GET
    @Path("/")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance getActivityTemplate() {
        if (!session.getPermissions().isEmpty() && session.getPermissions().get(1))
            return activity.instance();

        return ErrorTemplate.forbidden();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/list")
    public Response getActivities() {
        ActivityReturnDTO activityReturnDTO = activityBO.list();

        return Response.status(activityReturnDTO.getStatus()).entity(activityReturnDTO).build();
    }
}
