import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.Arrays;
import java.util.Base64;
import java.util.UUID;

@Path("/login")
public class loginEndpoint {

    @GET
    @Path("/loginuser/{logincredentials}")
    public String userCheck(@PathParam("logincredentials") String logindata){

        String decodedlogin = Arrays.toString(Base64.getDecoder().decode(logindata));

/////////////PRÜFUNG OB NUTZER VORHANDEN, WENN JA, VERGEBE TOKEN UND SPEICHERE TOKEN IN DB

                    String token = UUID.randomUUID().toString();

        return
    }
}
