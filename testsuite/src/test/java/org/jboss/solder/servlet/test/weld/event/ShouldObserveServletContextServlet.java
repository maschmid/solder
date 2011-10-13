package org.jboss.solder.servlet.test.weld.event;

import java.io.IOException;

import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.solder.beanManager.BeanManagerLocator;
import org.jboss.solder.logging.Logger;
import org.jboss.solder.servlet.WebApplication;
import org.jboss.solder.servlet.event.AbstractServletEventBridge;
import org.jboss.solder.servlet.event.ServletEventBridgeListener;
import org.jboss.solder.servlet.event.ServletEventBridgeServlet;
import org.jboss.solder.servlet.test.common.beanManager.ServletContextAttributeProviderServlet;

@WebServlet(ShouldObserveServletContextServlet.URL_PATTERN)
public class ShouldObserveServletContextServlet extends HttpServlet {
    public static final String URL_PATTERN = "/ShouldObserveServletContextServlet";
    public static final String EXPECTED_RESPONSE = "";              
    
    @Inject
    BeanManager manager;
    
    @Inject
    ServletContext ctx;
    
    @Inject
    ServletEventBridgeServlet servlet;
    
    @Inject
    ServletEventBridgeTestHelper observer;
    
    @Inject
    ServletEventBridgeListener listener;
    
    @Inject
    Logger logger;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        logger.info("doGet start");
        
        observer.reset();
        servlet.destroy();
        
        WebApplication webapp = (WebApplication) ctx.getAttribute(AbstractServletEventBridge.WEB_APPLICATION_ATTRIBUTE_NAME);
        
        
        listener.contextDestroyed(new ServletContextEvent(ctx));
        
        
        logger.info("doGet end");
       
        /*ServletContext ctx = mock(ServletContext.class);
        when(ctx.getServletContextName()).thenReturn("mock");
        ServletConfig cfg = mock(ServletConfig.class);
        when(cfg.getServletContext()).thenReturn(ctx);
        WebApplication webapp = new WebApplication(ctx);
        when(ctx.getAttribute(AbstractServletEventBridge.WEB_APPLICATION_ATTRIBUTE_NAME)).thenReturn(webapp);

        listener.contextInitialized(new ServletContextEvent(ctx));
        servlet.init(cfg);*/
        // servlet.destroy();
        // listener.contextDestroyed(new ServletContextEvent(ctx));
        //observer.assertObservations("WebApplication", webapp, webapp, webapp);
        //observer.assertObservations("ServletContext", ctx, ctx);
        
        /*BeanManagerLocator locator = new BeanManagerLocator();
        response.getWriter().append(locator.isBeanManagerAvailable() ? "BeanManager available" : "BeanManager not available");
        response.getWriter().append("; ");
        
        response.getWriter().append(manager.equals(locator.getBeanManager()) ? "manager == locator.getBeanManager()" : "manager != locator.getBeanManager()");*/ 
    }
}
