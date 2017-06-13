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
        
        // Ozark 1.0.0m2 add "WEB-INF/views" to prefix at resolveView(context) on ViewEngine.
        // And resolver.prefix is "" as defult value. 
        // It is convenient to write a controller of MVC1.0.
        // For example, if the return value of the MVC1.0 controller is "hello.html", that
        // means the Ozark use "/WEB-INF/view/hello.html" as template.
        //
        // But it is not convenient to specify fragment resources on a html template.
        // In the default settings, we must write like followings:
        // <div th:replace="/WEB-INF/views/common/layout.html :: header">HEADER</div>.
        // It's not good.
        // 
        // So, I defined resolver.prefix "/WEB-INF/views" and NOT add prefix on the ViewEngine.
        // By doing so, we can omit the prefix of the file path either in the controller or
        // the template.
        resolver.setPrefix(ViewEngine.DEFAULT_VIEW_FOLDER);
        
        // Default template mode is "html".
        //resolver.setTemplateMode(TemplateMode.HTML);
        
        // Default charset is null that represents to depend on environment.
        resolver.setCharacterEncoding("UTF-8");

        TemplateEngine engine = new TemplateEngine();
        engine.setTemplateResolver(resolver);
        engine.setMessageResolver(new MessageResolver());
        return engine;
    }
}