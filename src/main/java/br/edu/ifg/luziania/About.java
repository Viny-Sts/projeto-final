package br.edu.ifg.luziania;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("")
public class About {
    private final Template about;

    public About(Template about) {
        this.about = about;
    }

    @GET
    @Path("/about")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance getAbout(){
        return about.instance();
    }

}
