package br.edu.ifg.luziania.controller;

import br.edu.ifg.luziania.model.dto.AuthDTO;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("")
public class RegisterController {
    private final Template register;

    private AuthDTO user;

    public RegisterController(Template register) {
        this.register = register;
    }

    // Return register html file and renders it on user screen when they access "/register" url
    @GET
    @Path("/register")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance getRegister(){
        return register.instance();
    }

    // Don't return any html, instead "saves" user credentials in a variable
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/users")
    public Response authenticate(AuthDTO authDTO) {
        user = authDTO;

        return Response.status(Response.Status.CREATED).entity(authDTO).build();
    }

    // Don't return any html, instead get user credentials "saved" before
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/users")
    public Response getUsers() {
        if (user != null) {
            return Response.ok().entity(user).build();

        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
