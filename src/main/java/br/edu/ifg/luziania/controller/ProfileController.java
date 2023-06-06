package br.edu.ifg.luziania.controller;

import br.edu.ifg.luziania.model.bo.ProfileBO;
import br.edu.ifg.luziania.model.dto.ProfileDTO;
import br.edu.ifg.luziania.model.dto.ProfileReturnDTO;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("")
public class ProfileController {
    @Inject
    ProfileBO profileBO;

    private final Template profile;

    public ProfileController(Template profile) {
        this.profile = profile;
    }

    @GET
    @Path("/profile")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance getProfile(){
        return profile.instance();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/permissions")
    public Response authenticate(ProfileDTO profileDTO){
        ProfileReturnDTO profileReturnDTO = profileBO.save(profileDTO);

        return Response.status(profileReturnDTO.getStatus()).entity(profileReturnDTO).build();
    }
}
