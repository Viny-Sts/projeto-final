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
import javax.ws.rs.core.Response;

@Path("")
public class AdminController {
    @Inject
    Session session;
    private final Template admin;

    public AdminController(Template admin) {
        this.admin = admin;
    }

    @GET
    @Path("/admin")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance getAdmin(){
        if (session.getName().isEmpty())
            return ErrorTemplate.forbidden();

        return admin.instance();
    }
}
