import context.menu;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/admin")
public class adminEndpoints {

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean createMenu(menu m){

        menu newMenu = m;

        ///////////////SPEICHERN DES MENÜ-OBJEKTES IN DER DB
    }
}
