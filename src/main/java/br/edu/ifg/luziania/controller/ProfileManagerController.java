package br.edu.ifg.luziania.controller;

import br.edu.ifg.luziania.model.bo.ProfileBO;
import br.edu.ifg.luziania.model.dto.ProfileReturnDTO;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/profileManager")
public class ProfileManagerController {
    @Inject
    ProfileBO profileBO;

    private final Template profileManager;

    public ProfileManagerController(Template profileManager) {
        this.profileManager = profileManager;
    }

    @GET
    @Path("/")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance getProfileManagerTemplate() {
        return profileManager.instance();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/list")
    public Response getProfiles() {
        ProfileReturnDTO profileReturnDTO = profileBO.list();

        return Response.status(profileReturnDTO.getStatus()).entity(profileReturnDTO).build();
    }
}
