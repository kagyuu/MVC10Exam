/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.mvcexam.rest;

import javax.enterprise.context.RequestScoped;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author hondou.atsushi
 */
@RequestScoped
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HelloBean {
    private String name;
}
