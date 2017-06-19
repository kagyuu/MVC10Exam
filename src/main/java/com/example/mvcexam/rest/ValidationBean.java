/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.mvcexam.rest;

import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;
import lombok.Data;

/**
 *
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
