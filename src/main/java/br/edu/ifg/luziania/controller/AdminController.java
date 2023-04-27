package br.edu.ifg.luziania.controller;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("")
public class AdminController {
    private final Template admin;

    public AdminController(Template admin) {
        this.admin = admin;
    }

    @GET
    @Path("/admin")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance getAdmin(){
        return admin.instance();
    }


}
