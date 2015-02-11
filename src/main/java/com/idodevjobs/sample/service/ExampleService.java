package com.idodevjobs.sample.service;

import com.idodevjobs.sample.model.ExampleModel;

import javax.ws.rs.*;

@Path("/example")
@Produces("application/json")
public interface ExampleService {

    @GET
    @Path("/{id}")
    public ExampleModel get(@PathParam("id") String id);

    @POST
    public void post(ExampleModel exampleModel);

}