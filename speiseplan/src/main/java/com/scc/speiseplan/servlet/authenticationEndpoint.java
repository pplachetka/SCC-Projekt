package com.scc.speiseplan.servlet;

import com.scc.speiseplan.data.MyDBHandler;
import org.codehaus.jackson.map.ObjectMapper;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;

@Path("/authentication")
public class authenticationEndpoint {


    @POST
    @Path("/login")
    @Produces("application/json")
    public Response login(@FormParam("userID") int userId,@FormParam("password") String password) throws IOException {

        String token = "-1";//request.getParameter("token");
        String userData;

        //  response.getWriter().println(userId+password+token)

        // isUser ohne token abfrage da bei login eh noch kein token vorhanden
        if (new MyDBHandler().login(userId,password)) {
            //setToken
            token = new BigInteger(130, new SecureRandom()).toString(30);
            new MyDBHandler().setToken(userId,token);
            userData = new ObjectMapper().writeValueAsString(new MyDBHandler().getUserData(userId));


        }else{
            userData = "{ \"userID\":"+userId +", \"isAdmin\":\"-1\" }";
        }
        return Response.status(200).entity(userData).build();

    }

    // obsolete?
    @POST
    @Path("/isAdmin")
    @Produces("application/json")
    public Response isAdmin(@FormParam("token") String token){
    //ToDO

        new MyDBHandler().isAdmin(token);
        String userData= "1";

        return Response.status(200).entity(userData).build();
    }

}
