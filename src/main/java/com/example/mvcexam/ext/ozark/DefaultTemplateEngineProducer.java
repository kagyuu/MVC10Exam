/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.mvcexam.ext.ozark;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.mvc.engine.ViewEngine;
import javax.servlet.ServletContext;
import org.glassfish.ozark.engine.ViewEngineConfig;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

/**
 *
 * @author hondou.atsushi
 */
@Dependent
public class DefaultTemplateEngineProducer {

    @Produces
    @ViewEngineConfig
    public TemplateEngine getTemplateEngine(ServletContext context) {
        ServletContextTemplateResolver resolver = new ServletContextTemplateResolver(context);
        resolver.setPrefix(ViewEngine.DEFAULT_VIEW_FOLDER);
        TemplateEngine engine = new TemplateEngine();
        engine.setTemplateResolver(resolver);
        return engine;
    }
}