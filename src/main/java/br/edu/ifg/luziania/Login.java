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
    @Path("/login")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance getLogin(){
        return login.instance();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/autenticar")
    public Response authenticate(Auth auth){
        AuthReturn authReturn = new AuthReturn();
        if (auth.getEmail().equals("daniel@ifg.edu.br") &&
            auth.getPassword().equals("123")) {
            authReturn.setMessage("Usuário autenticado!");
            return Response.ok(authReturn, MediaType.APPLICATION_JSON).build();
        }
        else {
            authReturn.setMessage("Usuário não autenticado!");
            return Response.ok(authReturn, MediaType.APPLICATION_JSON).build();
        }
    }
}
