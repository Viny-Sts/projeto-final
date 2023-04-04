package br.edu.ifg.luziania;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("")
public class Login {
    private final Template login;

    public Login(Template login) {
        this.login = login;
    }

    @GET
    @Path("/autenticar")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance getLogin(){
        return login.instance();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response autenticar(Autenticacao autenticacao) {
        return Response.ok().build();
    }
}
