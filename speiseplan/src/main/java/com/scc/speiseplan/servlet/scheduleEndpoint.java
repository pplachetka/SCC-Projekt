package com.scc.speiseplan.servlet;

import com.scc.speiseplan.data.*;
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


    @Path("/setMenuItemSchedule")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response setMenuItemSchedule (MenuItemScheduleReceiver menuItemScheduleReceiver) {
        /** expected format:
         * {\"token\":\"1\",
         * \"menuItemSchedule\":[
         *     {\"date\":\"20190101\",
         *     \"position\":\"1\",
         *     \"menuItemID\":\"2\"}
         * ,{...}]
         * }";
         * with dateformat: YYYYMMDD **/

        String token = menuItemScheduleReceiver.getToken();

        if (! new MyDBHandler().isAdmin(token)){
            return Response.status(401).build();
        }
        ArrayList<MenuItemSchedule>  menuItemScheduleArray = menuItemScheduleReceiver.getMenuItemSchedule();

        System.out.println(token);
        for (MenuItemSchedule item : menuItemScheduleArray ) {
            new MyDBHandler()
                    .setMenuItemSchedule(
                            item.getDate(),
                            item.getMenuItemID(),
                            item.getPosition()
                    );
        }
        //ToDo: exception handling, inserted?
        return Response.status(200).build();
    }

    @Path("/getMenuItemSchedule")
    @GET
    @Produces("application/json")
    public Response getMenuItemSchedule(@QueryParam("startDate") int startDate,
                                        @QueryParam("endDate") int endDate) throws IOException {


        String menuItemScheduleList = new ObjectMapper().writeValueAsString(new MyDBHandler().getMenuItemSchedule(startDate,endDate));
        return Response.status(200).entity(menuItemScheduleList).build();

    }

    //ToDO:test
    @Path("/getMenuItemScheduleCustomerOrder")
    @GET
    @Produces("application/json")
    public Response getMenuItemScheduleCustomerOrder (@QueryParam("startDate") int startDate,
                                                      @QueryParam("endDate") int endDate,
                                                      @QueryParam("token") String token) throws IOException {
        if (! new MyDBHandler().isUser(token)){
            return Response.status(401).build();
        }
        int userId = new MyDBHandler().getUserDataByToken(token).getUserId();

        String menuItemScheduleCustomerOrderList = new ObjectMapper().writeValueAsString( new MyDBHandler().getMenuItemScheduleCustomerOrder(startDate,  endDate,  userId));

        return Response.status(200).entity(menuItemScheduleCustomerOrderList).build();
    }


    //ToDO:test
    @Path("/setMenuItemScheduleCustomerOrder")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    public Response setMenuItemScheduleCustomerOrder (MenuItemScheduleCustomerOrderReceiver MenuItemScheduleCustomerOrderReceiver ){

        /** expected format:
        * {"token": "token",
        * "MenuItemSchedule": [{
        *     "menuItemScheduleID": "1",
        *     "date": "20190101"
	    *     },
	    *     {...} ]
        * } */


        String token = MenuItemScheduleCustomerOrderReceiver.getToken();
        if (! new MyDBHandler().isUser(token)){
            return Response.status(401).build();
        }

        ArrayList<MenuItemSchedule>  menuItemScheduleArray = MenuItemScheduleCustomerOrderReceiver.getMenuItemSchedule();

        User user = new MyDBHandler().getUserDataByToken(token);

        for (MenuItemSchedule item : menuItemScheduleArray ) {
            new MyDBHandler().setMenuItemScheduleCustomerOrder(
                    user.getUserId()
                    ,item.getDate()
                    ,item.getMenuItemScheduleID()
                    );
        };

        return Response.status(200).build();
    }

}
