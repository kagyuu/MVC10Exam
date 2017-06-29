/*
 * An example for the MVC1.0m2 on the Glassfish 4.1.2 JEE7
 */
package com.example.mvcexam.ext.ozark;

import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.mvc.event.AfterControllerEvent;
import javax.mvc.event.AfterProcessViewEvent;
import javax.mvc.event.BeforeControllerEvent;
import javax.mvc.event.BeforeProcessViewEvent;
import javax.mvc.event.ControllerRedirectEvent;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

/**
 * MVC1.0の共通処理サンプル.
 * <pre>
 * コントローラ、Viewエンジンの開始終了に何らかの処理をはさむことができます。
 * ただし Model を操作することはできません。
 * </pre>
 * @author hondou.atsushi
 */
@Slf4j
@ApplicationScoped
public class CommonEventObservver {

    @Inject
    private HttpServletRequest req;

    public void onAfterControllerEvent(@Observes AfterControllerEvent event) {
        log.debug("AfterControllerEvent");

        req.setAttribute("__USER_NAME", req.getRemoteUser());

        try {
            req.setAttribute("__HOST_NAME", InetAddress.getLocalHost().getHostName());
        } catch (UnknownHostException e) {
            req.setAttribute("__HOST_NAME", "###");
        }
    }

    public void onAfterProcessViewEvent(@Observes AfterProcessViewEvent event) {
        log.debug("AfterProcessViewEvent");       
    }

    public void onBeforeControllerEvent(@Observes BeforeControllerEvent event) {
        log.debug("BeforeControllerEvent");      
    }

    public void onBeforeProcessViewEvent(@Observes BeforeProcessViewEvent event) {
        log.debug("BeforeProcessViewEvent");
    }

    public void onControllerRedirectEvent(@Observes ControllerRedirectEvent event) {
        log.debug("ControllerRedirectEvent");        
    }

}
