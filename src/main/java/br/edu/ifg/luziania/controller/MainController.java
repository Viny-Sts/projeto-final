package br.edu.ifg.luziania.controller;

import br.edu.ifg.luziania.model.bo.UserBO;
import br.edu.ifg.luziania.model.dto.AuthDTO;
import br.edu.ifg.luziania.model.util.ErrorTemplate;
import br.edu.ifg.luziania.model.util.Session;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("")
public class MainController {
    @Inject
    Session session;
    @Inject
    UserBO userBO;

    private final Template main;

    public MainController(Template main) {
        this.main = main;
    }

    @GET
    @Path("/main")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance getMainTemplate() {
        if (!session.getPermissions().isEmpty()) {
            return main.data("mainAccess", session.getPermissions().get(0),
                    "activityAccess", session.getPermissions().get(1),
                    "userManagement", session.getPermissions().get(2),
                    "profileManagement", session.getPermissions().get(3));
        }

        return ErrorTemplate.forbidden();
    }

    @POST
    @Path("/logout")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response logoutUser(AuthDTO authDTO) {
        return Response.ok(userBO.authenticate(authDTO.getEmail(), authDTO.getPassword()),
                MediaType.APPLICATION_JSON).build();
    }
}
