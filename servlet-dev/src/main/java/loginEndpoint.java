package main.java;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;
import java.util.Base64;
import java.util.UUID;

@Path("/login")
public class loginEndpoint {

    @POST
    @Path("/loginuser")
    @Produces(MediaType.TEXT_PLAIN)
    public String userCheck(String credentials){

        String decodedlogin = Arrays.toString(Base64.getDecoder().decode(credentials));

        String[] logindata = decodedlogin.split(":");

            String username = logindata[0];
            String password = logindata[1];



/////////////PRÜFUNG OB NUTZER VORHANDEN, WENN JA, VERGEBE TOKEN UND SPEICHERE TOKEN IN DB

        String token = username + ":" + UUID.randomUUID().toString();

        return token;
    }
}


