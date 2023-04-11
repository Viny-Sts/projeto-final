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
    public Response authenticate(AuthDTO authDTO){
        AuthReturnDTO authReturnDTO = new AuthReturnDTO();

        if (authDTO.getEmail().equals("rodrigo@gmail.com") && authDTO.getPassword().equals("projeto") ||
                authDTO.getEmail().equals("vinicius@gmail.com") && authDTO.getPassword().equals("123")) {
            authReturnDTO.setMessage("Usuário autenticado!");

            return Response.ok(authReturnDTO, MediaType.APPLICATION_JSON).build();
        }

        else if (authDTO.getEmail().equals("admin@staff.com") && authDTO.getPassword().equals("123")) {
            authReturnDTO.setMessage("Administrador autenticado!");

            return Response.ok(authReturnDTO, MediaType.APPLICATION_JSON).build();
        }

        else {
            authReturnDTO.setMessage("Usuário não autenticado!");

            return Response.ok(authReturnDTO, MediaType.APPLICATION_JSON).build();
        }
    }
}
