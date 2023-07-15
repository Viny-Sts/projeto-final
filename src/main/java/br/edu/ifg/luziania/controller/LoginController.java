package br.edu.ifg.luziania.controller;

import br.edu.ifg.luziania.model.bo.UserBO;
import br.edu.ifg.luziania.model.dto.AuthDTO;
import br.edu.ifg.luziania.model.util.Session;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
public class LoginController {
    @Inject
    Session session;
    @Inject
    UserBO userBO;

    private final Template login;

    public LoginController(Template login) {
        this.login = login;
    }

    @GET
    @Path("/")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance getLoginTemplate() {
        return login.data("mainAccess", !session.getPermissions().isEmpty() &&
                session.getPermissions().get(0));
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/auth")
    public Response authUser(AuthDTO authDTO) {
        return Response.ok(userBO.authenticate(authDTO.getEmail(), authDTO.getPassword()),
                MediaType.APPLICATION_JSON).build();
    }
}
