package br.edu.ifg.luziania.controller;

import br.edu.ifg.luziania.model.bo.UserBO;
import br.edu.ifg.luziania.model.dto.UserDTO;
import br.edu.ifg.luziania.model.dto.UserReturnDTO;
import br.edu.ifg.luziania.model.util.Session;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/signup")
public class UserController {
    @Inject
    Session session;
    @Inject
    UserBO userBO;

    private final Template user;

    public UserController(Template user) {
        this.user = user;
    }

    @GET
    @Path("/")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance getUserTemplate() {
        return user.data("userManagement", !session.getPermissions().isEmpty() &&
                session.getPermissions().get(2));
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/register")
    public Response saveUser(UserDTO userDTO) {
        UserReturnDTO userReturnDTO = userBO.save(userDTO);

        return Response.status(userReturnDTO.getStatus()).entity(userReturnDTO).build();
    }
}
