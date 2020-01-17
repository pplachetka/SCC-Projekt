package com.scc.speiseplan.servlet;

import com.scc.speiseplan.data.MyDBHandler;
import org.codehaus.jackson.map.ObjectMapper;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.math.BigDecimal;

@Path("/admin")
public class adminEndpoint {

    //DONE
    @Path("/getMenuItemList")
    @POST
    @Produces("application/json")
    public Response getMenuList(@FormParam("token") String token) throws IOException {

        if (! new MyDBHandler().isAdmin(token)){
            return Response.status(401).build();
        }
        String menuItemList = new ObjectMapper().writeValueAsString(new MyDBHandler().getMenuItemList());
        return Response.status(200).entity(menuItemList).build();

    }

    //DONE
    @Path("/setMenuItem")
    @POST
    public Response setMenuItem(@FormParam("description") String Description,
                                @FormParam("costs") BigDecimal Costs,
                                @FormParam("token") String token) {

        if (! new MyDBHandler().isAdmin(token)){
            return Response.status(401).build();
        }

        new MyDBHandler().insertMenuItem( Description,  Costs);

        //ToDo: schauen ob das menuItem tatsächlich abgebildet ist
        // Exception handling unknown error?
        return Response.status(200).build();
    }

}
