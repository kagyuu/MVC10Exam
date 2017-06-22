/*
 * An example for the MVC1.0m2 on the Glassfish 4.1.2 JEE7
 */
package com.example.mvcexam.rest;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.mvc.Models;
import javax.mvc.annotation.Controller;
import javax.mvc.annotation.CsrfValid;
import javax.mvc.binding.BindingResult;
import javax.validation.Valid;
import javax.validation.executable.ExecutableType;
import javax.validation.executable.ValidateOnExecution;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import lombok.extern.slf4j.Slf4j;

/**
 * REST Web Service
 * @author hondou.atsushi
 */
@Controller
@Path("/validation")
@RequestScoped
@Slf4j
public class ValidationController {
    @Context
    private UriInfo context;
        
    @Inject
    private Models models;

    @Inject
    private BindingResult bindingResult;
    
    /**
     * Creates a new instance of ViewResource
     */
    public ValidationController() {
    }
    
    @Path("/init")
    @GET
    public String validateForm() {
        return "commentform.html";
    }    

    /**
     * Retrieves representation of an instance of com.example.mvcexam.rest.ViewResource
     * @param bean
     * @return an instance of java.lang.String
     */
    @Path("/submit")
    @POST
    @CsrfValid
    @ValidateOnExecution(type=ExecutableType.NONE)
    public String validateForm(@Valid @BeanParam ValidationBean bean) {
        log.info("validateForm was called!");
        
        if (bindingResult.isFailed()) {
            models.put("form", bean);
            models.put("bindingResult", bindingResult);            
            return "fail.html";
        }
       
        models.put("form", bean);
        return "success.html";
    }    
}
