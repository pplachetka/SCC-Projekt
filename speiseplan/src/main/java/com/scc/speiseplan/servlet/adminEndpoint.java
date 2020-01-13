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
    public Response getMenuList() throws IOException {

        String menuItemList = new ObjectMapper().writeValueAsString(new MyDBHandler().getMenuItemList());
        return Response.status(200).entity(menuItemList).build();

    }

    //DONE
    @Path("/setMenuItem")
    @POST
    public Response setMenuItem(@FormParam("Description") String Description,
                                @FormParam("Costs") BigDecimal Costs,
                                @FormParam("token") int token) {



        new MyDBHandler().insertMenuItem( Description,  Costs);

        //ToDo:Tokencheck => admin?
        //ToDo: schauen ob das menuItem tatsächlich abgebildet ist,
        //ToDo: exception handling typecast auf costs ansonsten status bad
        return Response.status(200).build();
    }

}
