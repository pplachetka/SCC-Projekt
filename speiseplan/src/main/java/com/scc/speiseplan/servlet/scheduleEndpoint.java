package com.scc.speiseplan.servlet;

import com.scc.speiseplan.data.MenuItemSchedule;
import com.scc.speiseplan.data.MenuItemScheduleReceiver;
import com.scc.speiseplan.data.MyDBHandler;
import org.codehaus.jackson.map.ObjectMapper;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Path("/schedule")
public class scheduleEndpoint {

/*
    /* using jersey build in jackson lib to import json directly into POJO
    * https://mkyong.com/webservices/jax-rs/restful-java-client-with-jersey-client/
    * accepting a list of jsons
    * https://stackoverflow.com/questions/32510240/how-to-accept-json-array-input-in-jersey-rest-webservice */
/*
    @Path("/setMenuItemSchedule")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response setMenuItemSchedule (List<MenuItemSchedule> menuItemSchedule) {
        */
    @Path("/setMenuItemSchedule")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response setMenuItemSchedule (MenuItemScheduleReceiver menuItemScheduleReceiver) {
        /* expecting:
                {   token:\"1\",
                   menuItemSchedule:[{\"date\":\"20190101\",
                                        \"position\":\"1\",
                                        \"menuItemID\":\"2\"},
                                     {...}
                                     ]
                }";
        * with dateformat: YYYYMMDD */

        int token = menuItemScheduleReceiver.getToken();
        ArrayList<MenuItemSchedule>  menuItemScheduleArray = menuItemScheduleReceiver.getMenuItemSchedule();

        // ToDo: check for token
        System.out.println(token);
        for (MenuItemSchedule item : menuItemScheduleArray ) {
            new MyDBHandler()
                    .setMenuItemSchedule(
                            item.getDate(),
                            item.getMenuItemID(),
                            item.getPosition()
                    );
        }

        //System.out.println(menuItemSchedule.get(0).toString());


        // müsste eig ein MenuItemSchedule

        //ToDo:Tokencheck => admin?
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
