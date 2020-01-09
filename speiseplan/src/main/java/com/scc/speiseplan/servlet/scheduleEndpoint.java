package com.scc.speiseplan.servlet;

import com.scc.speiseplan.data.MyDBHandler;
import org.codehaus.jackson.map.ObjectMapper;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.sql.Date;

@Path("/schedule")
public class scheduleEndpoint {

    //DONE
    @Path("/setMenuItemSchedule")
    @GET
    public Response setMenuItemSchedule (@QueryParam("date") int date,
                                         @QueryParam("position") int position,
                                         @QueryParam("menuItemID") int menuItemID) {

        //ToDo:Tokencheck => admin?
        //dateformat: YYYYMMDD
        System.out.println("date: " +date + "| position: " + position + "| menuItemID: "+menuItemID);
        new MyDBHandler().setMenuItemSchedule(date, position, menuItemID);
        //ToDo: exception handling, inserted?
        //ToDO: typecast date as date?

        return Response.status(200).build();
    }

    //ToDO
    @Path("/getMenuItemSchedule")
    @GET
    @Produces("application/json")
    public Response getMenuItemSchedule(@QueryParam("startDate") int startDate,
                                        @QueryParam("endDate") int endDate){

        new MyDBHandler().getMenuItemSchedule(startDate,endDate);

        return Response.status(200).build();
    }
}
