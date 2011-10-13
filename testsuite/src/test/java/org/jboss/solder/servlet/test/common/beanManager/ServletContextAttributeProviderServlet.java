package org.jboss.solder.servlet.test.common.beanManager;

import java.io.IOException;

import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.solder.beanManager.BeanManagerLocator;

@WebServlet(ServletContextAttributeProviderServlet.URL_PATTERN)
public class ServletContextAttributeProviderServlet extends HttpServlet {

    public static final String URL_PATTERN = "/ServletContextAttributeProviderServlet";
    public static final String EXPECTED_RESPONSE = 
        "BeanManager available; manager == locator.getBeanManager()";
                
    
    @Inject
    BeanManager manager;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        BeanManagerLocator locator = new BeanManagerLocator();
        response.getWriter().append(locator.isBeanManagerAvailable() ? "BeanManager available" : "BeanManager not available");
        response.getWriter().append("; ");
        
        response.getWriter().append(manager.equals(locator.getBeanManager()) ? "manager == locator.getBeanManager()" : "manager != locator.getBeanManager()"); 
    }
}
