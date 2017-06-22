/*
 * An example for the MVC1.0m2 on the Glassfish 4.1.2 JEE7
 */
package com.example.mvcexam.rest;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.mvc.Models;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * CsrfExceptionMapper.
 * Don't forget ApplicationScoped.
 * If the annotation is not, Glassfish says 
 * "There was no object available for injection at SystemInjecteeImpl"
 * beceuse this class is not managed by CDI.
 * @author hondou.atsushi
 */
@Provider
@ApplicationScoped
public class CsrfExceptionMapper implements ExceptionMapper<ForbiddenException> {
    
    @Inject
    private Models models;

    @Override
    public Response toResponse(ForbiddenException exception) {
        models.put("message", exception.getMessage());

        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity("error.html")
                .build();
    }
}
