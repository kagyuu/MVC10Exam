/*
 * An example for the MVC1.0m2 on the Glassfish 4.1.2 JEE7
 */
package com.example.mvcexam.rest;

import javax.enterprise.context.RequestScoped;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Hello Bean.
 * @author hondou.atsushi
 */
@RequestScoped
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HelloBean {
    private String name;
}
