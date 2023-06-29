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

@Path("/sign-up")
public class SignupController {
    @Inject
    UserBO userBO;

    private final Template register;

    public SignupController(Template register) {
        this.register = register;
    }

    @GET
    @Path("/")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance getRegister() {
        return register.instance();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/register")
    public Response authenticate(UserDTO userDTO) {
        UserReturnDTO userReturnDTO = userBO.save(userDTO);

        return Response.status(userReturnDTO.getStatus()).entity(userReturnDTO).build();
    }
}
