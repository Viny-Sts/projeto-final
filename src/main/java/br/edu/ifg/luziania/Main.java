package br.edu.ifg.luziania;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("")
public class Main {
    private final Template main;

    public Main(Template main) {
        this.main = main;
    }

    @GET
    @Path("/main")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance getMain(){
        return main.instance();
    }

}
