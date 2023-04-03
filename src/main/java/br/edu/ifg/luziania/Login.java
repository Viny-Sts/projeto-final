package br.edu.ifg.luziania;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("")
public class Login {
    private final Template login;

    public Login(Template login) {
        this.login = login;

    }

    @GET
    @Path("/login")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance getLogin(){
        return login.instance();
    }

}
