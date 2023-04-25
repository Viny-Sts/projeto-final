package br.edu.ifg.luziania.controller;

import br.edu.ifg.luziania.model.dto.AuthDTO;
import br.edu.ifg.luziania.model.dto.AuthReturnDTO;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("")
public class RegisterController {
    private final Template register;

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
    @Path("/register-authenticate")
    public Response authenticate(AuthDTO authDTO){
        AuthReturnDTO authReturnDTO = new AuthReturnDTO();

        authReturnDTO.setMessage("Successfully registered. Now you can sign-in");
        return Response.ok(authReturnDTO, MediaType.APPLICATION_JSON).build();
    }
}
