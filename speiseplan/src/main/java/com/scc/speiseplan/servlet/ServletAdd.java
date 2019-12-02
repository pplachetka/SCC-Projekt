package com.scc.speiseplan.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.awt.*;
import java.io.IOException;

@Path("add")
public class ServletAdd extends HttpServlet {



    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt(){
        return "Got IT";
    }
    /*
    public String add(@QueryParam("num1") String num1, @QueryParam("num2") String num2){
        int i = Integer.parseInt(num1);
        int j = Integer.parseInt(num2);

        int k = i+j;
        return ("Outout "+k);
    }

    */
/*
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int i = Integer.parseInt(request.getParameter("num1"));
        int j = Integer.parseInt(request.getParameter("num2"));

        int k = i+j;
        response.getWriter().println("Outout "+k);
    }
*/

}
