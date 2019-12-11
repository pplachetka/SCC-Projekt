package com.scc.speiseplan.servlet;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("message")
public class JerseyService
{
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt()
    {
        return "Hello World !! - Jersey 2";
    }
}