package WebService;

import br.com.desafiosefaz.dao.UsuarioDao;
import br.com.desafiosefaz.domain.Usuario;
import com.google.gson.Gson;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

@Path("generic")
public class ServiceDesafio {

    @Context
    private UriInfo context;

    public ServiceDesafio() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("Usuario/list")
    public String ListUsuario() {
        UsuarioDao dao = new UsuarioDao();
        Gson g = new Gson();
        try {
            List<Usuario> lista = dao.listar("Usuario.listar");
            return g.toJson(lista);
        } catch (Exception ex) {
            return g.toJson("Erro:" + ex);
        }
    }
}
