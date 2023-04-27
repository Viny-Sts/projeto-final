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

    @GET
    @Path("/register")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance getRegister(){
        return register.instance();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/users")
    public Response authenticate(AuthDTO authDTO) {
        user = authDTO;

        return Response.status(Response.Status.CREATED).entity(authDTO).build();
    }

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
