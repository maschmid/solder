/*
 * JBoss, Home of Professional Open Source
 * Copyright 2010, Red Hat Middleware LLC, and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.solder.servlet.test.common.beanManager;

import java.net.URL;

import javax.enterprise.inject.Instance;
import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import junit.framework.Assert;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.solder.servlet.ImplicitServletObjectsProducer;
import org.jboss.solder.servlet.ServerInfo;
import org.jboss.solder.servlet.beanManager.ServletContextAttributeProvider;
import org.jboss.solder.servlet.event.ServletEventBridgeListener;
import org.jboss.solder.servlet.http.HttpServletRequestContext;
import org.jboss.solder.servlet.test.common.util.Deployments;
import org.jboss.solder.servlet.test.common.util.StreamReaderUtil;
import org.jboss.solder.beanManager.BeanManagerLocator;
import org.jboss.solder.beanManager.BeanManagerProvider;
import org.jboss.shrinkwrap.api.Archive;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author <a href="http://community.jboss.org/people/dan.j.allen">Dan Allen</a>
 */
@RunWith(Arquillian.class)
@RunAsClient
public class ServletContextAttributeProviderTest {
    @Deployment(testable = false)
    public static Archive<?> createDeployment() {
        return Deployments.createBeanWebArchive()
                .addClasses(ServletContextAttributeProviderTest.class, ServletContextAttributeProviderServlet.class);
                //.addClasses(ServletContextAttributeProvider.class, HttpServletRequestContext.class)
                //.addPackage(ImplicitServletObjectsProducer.class.getPackage())
                //.addPackages(true, ServletEventBridgeListener.class.getPackage())
                //.addAsServiceProvider(BeanManagerProvider.class, ServletContextAttributeProvider.class);
    }
    
    @ArquillianResource
    URL deploymentUrl;

    /*@Inject
    BeanManager manager;

    @Inject
    ServletEventBridgeListener listener;*/

    // TODO this should be in a separate test
   /* @Inject
    @ServerInfo
    Instance<String> serverInfoProvider;*/

    @Test
    public void should_register_and_locate_bean_manager() throws Exception {
        
        String requestUrl = deploymentUrl + ServletContextAttributeProviderServlet.URL_PATTERN.substring(1);
        String body = StreamReaderUtil.readAllAndClose(new URL(requestUrl).openStream());
        
        Assert.assertEquals(ServletContextAttributeProviderServlet.EXPECTED_RESPONSE, body);
        
        /*String MOCK_SERVLET_CONTEXT = "Mock Servlet Context";

        ServletContext ctx = mock(ServletContext.class);
        when(ctx.getServerInfo()).thenReturn(MOCK_SERVLET_CONTEXT);
        listener.contextInitialized(new ServletContextEvent(ctx));
        verify(ctx).setAttribute(BeanManager.class.getName(), manager);

        assertEquals(MOCK_SERVLET_CONTEXT, serverInfoProvider.get());

        when(ctx.getAttribute(BeanManager.class.getName())).thenReturn(manager);
        BeanManagerLocator locator = new BeanManagerLocator();
        assertTrue(locator.isBeanManagerAvailable());
        assertEquals(manager, locator.getBeanManager());*/
    }
}
