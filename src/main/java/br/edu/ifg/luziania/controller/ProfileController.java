package br.edu.ifg.luziania.controller;

import br.edu.ifg.luziania.model.bo.ProfileBO;
import br.edu.ifg.luziania.model.dto.ProfileDTO;
import br.edu.ifg.luziania.model.dto.ProfileReturnDTO;
import br.edu.ifg.luziania.model.util.ErrorTemplate;
import br.edu.ifg.luziania.model.util.Session;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/profile")
public class ProfileController {
    @Inject
    Session session;

    @Inject
    ProfileBO profileBO;

    private final Template profile;

    public ProfileController(Template profile) {
        this.profile = profile;
    }

    @GET
    @Path("/")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance getProfile() {
        if (!session.getPermissions().isEmpty() && session.getPermissions().get(3))
            return profile.instance();

        return ErrorTemplate.forbidden();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/list")
    public Response getPermissions() {
        ProfileReturnDTO profileReturnDTO = profileBO.list();

        return Response.status(profileReturnDTO.getStatus()).entity(profileReturnDTO).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/register")
    public Response authenticate(ProfileDTO profileDTO) {
        ProfileReturnDTO profileReturnDTO = profileBO.save(profileDTO);

        return Response.status(profileReturnDTO.getStatus()).entity(profileReturnDTO).build();
    }
}
