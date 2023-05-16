package br.edu.ifg.luziania.controller;

import br.edu.ifg.luziania.model.dto.AuthDTO;
import br.edu.ifg.luziania.model.dto.UserDTO;
import br.edu.ifg.luziania.model.entity.User;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("")
public class RegisterController {
    private final Template register;

    private UserDTO user;

    public RegisterController(Template register) {
        this.register = register;
    }

    // Return register html file and renders it on user screen when they access "/register" url
    /*O método getRegister() é um método HTTP GET que é mapeado para a rota "/register" usando a anotação @Path. Ele retorna um objeto TemplateInstance, que é uma instância do modelo HTML que será usada para gerar a resposta HTML da página de registro. A resposta é produzida com o tipo de mídia "MediaType.TEXT_HTML", especificado pela anotação @Produces.*/
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
    public Response authenticate(UserDTO userDTO) {
        user = userDTO;

        return Response.status(Response.Status.CREATED).entity(userDTO).build();
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
