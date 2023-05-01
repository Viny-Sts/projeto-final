package br.edu.ifg.luziania.controller;

import br.edu.ifg.luziania.model.dto.AuthDTO;
import br.edu.ifg.luziania.model.dto.AuthReturnDTO;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("")
public class LoginController {
    private final Template login;

    public LoginController(Template login) {
        this.login = login;
    }

    // Return login html file and renders it on user screen when they access "/login" url
    @GET
    @Path("/login")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance getLogin(){
        return login.instance();
    }

    // Don't return any html, instead authenticate verifying user credentials
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/authenticate")
    public Response authenticate(AuthDTO authDTO){
        AuthReturnDTO authReturnDTO = new AuthReturnDTO();

        if (authDTO.getEmail().equals("rodrigo@gmail.com") && authDTO.getPassword().equals("123") ||
                authDTO.getEmail().equals("vinicius@gmail.com") && authDTO.getPassword().equals("123")) {
            authReturnDTO.setMessage("Connected as user");

            return Response.ok(authReturnDTO, MediaType.APPLICATION_JSON).build();
        }

        else if (authDTO.getEmail().equals("admin@staff.com") && authDTO.getPassword().equals("123")) {
            authReturnDTO.setMessage("Connected as administrator");

            return Response.ok(authReturnDTO, MediaType.APPLICATION_JSON).build();
        }

        else {
            authReturnDTO.setMessage("Invalid credentials");

            return Response.ok(authReturnDTO, MediaType.APPLICATION_JSON).build();
        }
    }
}
