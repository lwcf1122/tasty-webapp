package com.tasty.rest.resource;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Created by flao on 5/16/15.
 */
@Resource
@Path("/tasty")
public class SampleResource {

    @GET
    @Produces("text/plain")
    @Path("/app")
    public String get() {
        return "welcome to Tasty!";
    }
}
