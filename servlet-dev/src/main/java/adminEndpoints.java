import context.menu;
import main.java.membercheck;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

@Path("/admin")
public class adminEndpoints {

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    public int createMenu(menu m){                     //todo: TOKEN ÜBERGEBEN
        membercheck mc = new membercheck();

        switch (mc.kindOfMemeber(token)){
            case 0:{
                return 401;
                break;
            }
            case 1:{
                return 403;
                break;
            }
            case 2:{
                menu newMenu = m;
                break;
            }
        }



        ///////////////SPEICHERN DES MENÜ-OBJEKTES IN DER DB
    }
}
