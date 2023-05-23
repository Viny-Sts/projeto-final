package br.edu.ifg.luziania.controller;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("")
public class ProfileController {
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
}
