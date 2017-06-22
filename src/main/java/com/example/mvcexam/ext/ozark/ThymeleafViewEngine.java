/*
 * An example for the MVC1.0m2 on the Glassfish 4.1.2 JEE7
 */
package com.example.mvcexam.ext.ozark;

import java.io.IOException;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.mvc.engine.ViewEngineContext;
import javax.mvc.engine.ViewEngineException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.glassfish.ozark.engine.ViewEngineBase;
import org.glassfish.ozark.engine.ViewEngineConfig;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

/**
 * Execute Template Engine.
 * @author hondou.atsushi
 */
@Slf4j
@ApplicationScoped
public class ThymeleafViewEngine extends ViewEngineBase {

    @Inject
    private ServletContext servletContext;

    @Inject
    @ViewEngineConfig
    private TemplateEngine engine;

    @Override
    public boolean supports(String view) {
        return view.endsWith(".html");
    }

    @Override
    public void processView(ViewEngineContext context) throws ViewEngineException {
        try {
            HttpServletRequest request = context.getRequest();
            HttpServletResponse response = context.getResponse();
            WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
            ctx.setVariables(context.getModels());
            
            // Don't add prefix to the URL of the template.
            // For more details, see DefaultTemplateEngineProducer.java
            engine.process(context.getView() /* resolveView(context) */
                    , ctx, response.getWriter());
        } catch (IOException e) {
            throw new ViewEngineException(e);
        }
    }
}
