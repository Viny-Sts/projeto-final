package br.edu.ifg.luziania;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

import javax.print.attribute.standard.Media;
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

    //@POST
    //@Produces(MediaType.APPLICATION_JSON)
    //@Consumes(MediaType.APPLICATION_JSON)
    //public Response autenticar(Autenticacao autenticacao) {
        //if (autenticacao.getEmail().equals("test") &&
            //autenticacao.getSenha().equals("SenhaTest")) {

            //return Response.ok("{data:\n Usuário inválido \n}", MediaType.APPLICATION_JSON);
        //}

        //return Response.ok().build();
    //}
}
