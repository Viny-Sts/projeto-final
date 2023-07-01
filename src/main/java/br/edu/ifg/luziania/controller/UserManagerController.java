package br.edu.ifg.luziania.controller;

import br.edu.ifg.luziania.model.bo.UserBO;
import br.edu.ifg.luziania.model.dto.UserReturnDTO;
import br.edu.ifg.luziania.model.util.ErrorTemplate;
import br.edu.ifg.luziania.model.util.Session;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/userManager")
public class UserManagerController {
    @Inject
    Session session;
    @Inject
    UserBO userBO;

    private final Template userManager;

    public UserManagerController(Template userManager) {
        this.userManager = userManager;
    }

    @GET
    @Path("/")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance getUserManagerTemplate() {
        if (!session.getPermissions().isEmpty() && session.getPermissions().get(2))
            return userManager.instance();

        return ErrorTemplate.forbidden();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/list")
    public Response getUsers() {
        UserReturnDTO userReturnDTO = userBO.list();

        return Response.status(userReturnDTO.getStatus()).entity(userReturnDTO).build();
    }
}
