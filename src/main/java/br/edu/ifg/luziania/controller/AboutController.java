package br.edu.ifg.luziania.controller;

import br.edu.ifg.luziania.model.util.ErrorTemplate;
import br.edu.ifg.luziania.model.util.Session;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("")
public class AboutController {
    @Inject
    Session session;

    private final Template about;

    public AboutController(Template about) {
        this.about = about;
    }

    @GET
    @Path("/about")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance getAboutTemplate() {
        return about.data("mainAccess", !session.getPermissions().isEmpty() &&
                session.getPermissions().get(0));
    }
}
