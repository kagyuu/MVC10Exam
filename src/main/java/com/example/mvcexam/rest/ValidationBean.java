/*
 * An example for the MVC1.0m2 on the Glassfish 4.1.2 JEE7
 */
package com.example.mvcexam.rest;

import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;
import lombok.Data;

/**
 * Validation Bean.
 * @author hondou.atsushi
 */
@Data
public class ValidationBean {
    @FormParam("name")
    @Size(min = 1, max = 255)
    private String name;
     
    @FormParam("comment")
    @Size(min = 1, max = 255)
    private String comment;    
}
