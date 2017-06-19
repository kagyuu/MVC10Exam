/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.mvcexam.rest;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.mvc.security.Csrf;
import javax.ws.rs.core.Application;

/**
 *
 * @author hondou.atsushi
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    @Override
    public Map<String, Object> getProperties() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(Csrf.CSRF_PROTECTION, Csrf.CsrfOptions.EXPLICIT);
        return properties;
    }

    /**
     * Do not modify addRestResourceClasses() method. It is automatically
     * populated with all resources defined in the project. If required, comment
     * out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.example.mvcexam.rest.CsrfExceptionMapper.class);
        resources.add(com.example.mvcexam.rest.GenericResource.class);
        resources.add(com.example.mvcexam.rest.HelloController.class);
        resources.add(com.example.mvcexam.rest.ValidationController.class);
    }
}
