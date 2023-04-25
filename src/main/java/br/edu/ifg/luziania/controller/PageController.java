package br.edu.ifg.luziania.controller;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("")
public class PageController {
    private final Template index;
    private final Template about;
    private final Template main;
    private final Template admin;

    public PageController(Template index, Template about, Template main, Template admin) {
        this.index = index;
        this.about = about;
        this.main = main;
        this.admin = admin;
    }

    @GET
    @Path("/")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance getIndex(){
        return index.instance();
    }

    @GET
    @Path("/about")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance getAbout(){
        return about.instance();
    }

    @GET
    @Path("/main")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance getMain(){
        return main.instance();
    }

    @GET
    @Path("/admin")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance getAdmin(){
        return admin.instance();
    }
}
