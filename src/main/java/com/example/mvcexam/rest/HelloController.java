/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.mvcexam.rest;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.mvc.Models;
import javax.mvc.annotation.Controller;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import lombok.extern.slf4j.Slf4j;

/**
 * REST Web Service
 *
 * @author hondou.atsushi
 */
@Controller
@Path("/hello")
@RequestScoped
@Slf4j
public class HelloController {

    @Context
    private UriInfo context;
    
    @Inject
    private HelloBean helloBean;
    
    @Inject
    private Models models;

    /**
     * Creates a new instance of ViewResource
     */
    public HelloController() {
    }

    /**
     * Retrieves representation of an instance of com.example.mvcexam.rest.ViewResource
     * @param pageid
     * @return an instance of java.lang.String
     */
    @GET
    public String helloMVC() {
        log.info("MVC1.0 was called!");
        helloBean.setName("Cathy");
        models.put("helloBean", helloBean);
        return "hello.html";
    }
}
