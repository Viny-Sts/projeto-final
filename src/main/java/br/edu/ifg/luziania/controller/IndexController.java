package br.edu.ifg.luziania.controller;

import br.edu.ifg.luziania.model.util.Session;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("")
public class IndexController {
    @Inject
    Session session;

    private final Template index;

    public IndexController(Template index) {
        this.index = index;
    }

    @GET
    @Path("/")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance getIndexTemplate() {
        return index.data("mainAccess", !session.getPermissions().isEmpty() &&
                session.getPermissions().get(0));
    }
}
