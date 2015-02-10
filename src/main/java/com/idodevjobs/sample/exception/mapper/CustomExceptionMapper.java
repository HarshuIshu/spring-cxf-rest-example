package com.idodevjobs.sample.exception.mapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class CustomExceptionMapper implements ExceptionMapper<CustomException> {
    @Override
    public Response toResponse(CustomException e) {
        return Response.status(Response.Status.NOT_IMPLEMENTED).header("Content-Type", "application/json").entity("CustomException 501").build();
    }
}