/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import org.glassfish.ozark.engine.ViewEngineBase;
import org.glassfish.ozark.engine.ViewEngineConfig;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

/**
 *
 * @author hondou.atsushi
 */
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
			engine.process(resolveView(context), ctx, response.getWriter());
		} catch (IOException e) {
			throw new ViewEngineException(e);
		}
	}
}