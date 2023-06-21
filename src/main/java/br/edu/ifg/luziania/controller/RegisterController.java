package br.edu.ifg.luziania.controller;

import br.edu.ifg.luziania.model.bo.UserBO;
import br.edu.ifg.luziania.model.dto.UserDTO;
import br.edu.ifg.luziania.model.dto.UserReturnDTO;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("")
public class RegisterController {
    @Inject
    UserBO userBO;

    private final Template register;

    public RegisterController(Template register) {
        this.register = register;
    }

    @GET
    @Path("/register")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance getRegister() {
        return register.instance();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/list-users")
    public Response getUsers() {
        return Response.ok(userBO.list(), MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/users")
    public Response authenticate(UserDTO userDTO) {
        UserReturnDTO userReturnDTO = userBO.save(userDTO);

        return Response.status(userReturnDTO.getStatus()).entity(userReturnDTO).build();
    }
}
