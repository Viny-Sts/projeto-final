package br.edu.ifg.luziania.controller;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("")
public class PageController {
    private final Template index;
    private final Template about;
    private final Template main;
    private final Template profile;
    //esse constrututor receber parametros do tipo tamplate e os atribui às variaveis de instancia abaixo.
    public PageController(Template index, Template about, Template main, Template profile) {
        this.index = index;
        this.about = about;
        this.main = main;
        this.profile = profile;
    }
    //A classe PageController possui três métodos anotados com @GET e @Path para atender as solicitações HTTP GET para as rotas /, /about e main. Cada um desses métodos retorna uma instância do modelo de página correspondente através do método instance() da variável de instância correspondente.

    //Esses métodos são anotados com @Produces(MediaType.TEXT_HTML), o que significa que a resposta da solicitação deve ser um documento HTML. Isso indica que a aplicação deve gerar páginas HTML como resposta para essas solicitações. */

    @GET
    @Path("/")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance getIndex(){
        return index.instance();
    }
    //
    @GET
    @Path("/about")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance getAbout(){
        return about.instance();
    }
   //
    @GET
    @Path("/main")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance getMain(){
        return main.instance();
    }
    @GET
    @Path("/profile")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance getProfile(){
        return profile.instance();
    }
}
