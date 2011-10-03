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
package org.jboss.solder.servlet.test.common.http;


import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.Arrays;
import java.util.List;


import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.Typed;
import javax.enterprise.inject.Specializes;
import javax.enterprise.inject.spi.Extension;
import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import junit.framework.Assert;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.solder.servlet.ServletExtension;
import org.jboss.solder.servlet.ServletRequestContext;
import org.jboss.solder.servlet.http.CookieParam;
import org.jboss.solder.servlet.http.DefaultValue;
import org.jboss.solder.servlet.http.HeaderParam;
import org.jboss.solder.servlet.http.ImplicitHttpServletObjectsProducer;
import org.jboss.solder.servlet.http.RedirectBuilder;
import org.jboss.solder.servlet.http.RedirectBuilderImpl;
import org.jboss.solder.servlet.http.RequestParam;
import org.jboss.solder.servlet.http.TypedParamValue;
import org.jboss.solder.servlet.test.common.util.Deployments;
import org.jboss.shrinkwrap.api.Archive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jboss.solder.servlet.event.ImplicitServletObjectsHolder;
import org.jboss.solder.servlet.http.HttpServletRequestContext;
import org.jboss.solder.servlet.http.ContextPath;


import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * 
 */ 
public class MockHttpServletObjectsProducer extends ImplicitHttpServletObjectsProducer {

    private static final String IMPLICIT_PARAM = "implicit";
    private static final String EXPLICIT_PARAM = "explicit";
    private static final String MISSING_PARAM = "missing";
    private static final String IMPLICIT_VALUE = IMPLICIT_PARAM + "Value";
    private static final String EXPLICIT_VALUE = EXPLICIT_PARAM + "Value";
    private static final String DEFAULT_VALUE = "defaultValue";

/*    @Inject
    private ImplicitServletObjectsHolder holder;

    @Override
    @Produces
    @RequestScoped
    @Specializes
    protected HttpSession getHttpSession() {
        return holder.getHttpSession();
    }

    @Override
    @Produces
    @Typed(HttpServletRequestContext.class)
    @RequestScoped
    @Specializes
    protected HttpServletRequestContext getHttpServletRequestContext() {
        return holder.getHttpServletRequestContext();
    }

    
    @Override
    @Produces
    @Typed(HttpServletResponse.class)
    @RequestScoped
    @Specializes
    protected HttpServletResponse getHttpServletResponse() {
        return holder.getHttpServletResponse();
    }

    @Override
    @Produces
    @RequestScoped
    @Specializes
    protected List<Cookie> getCookies() {
        return Arrays.asList(getHttpServletRequest().getCookies());
    }

    @Override
    @Produces
    @ContextPath
    @Specializes
    protected String getContextPath() {
        return getHttpServletRequest().getContextPath();
    }*/


    @Override
    @Produces
//    @Specializes
    @Typed(HttpServletRequest.class)
//    @RequestScoped
    protected HttpServletRequest getHttpServletRequest() {
        HttpServletRequest req = mock(HttpServletRequest.class);

        Map<String, String[]> parameters = new HashMap<String, String[]>();
        parameters.put(IMPLICIT_PARAM, new String[]{IMPLICIT_VALUE});
        parameters.put(EXPLICIT_PARAM, new String[]{EXPLICIT_VALUE});
        parameters.put("page", new String[]{"1"});
        parameters.put("pageSize", new String[]{"25"});
        parameters.put("suit", new String[]{Suit.DIAMONDS.name()});
        parameters.put("airDate", new String[]{"2010-08-01 20:00"});
        when(req.getParameterMap()).thenReturn(parameters);
        when(req.getParameter(IMPLICIT_PARAM)).thenReturn(IMPLICIT_VALUE);
        when(req.getParameter(EXPLICIT_PARAM)).thenReturn(EXPLICIT_VALUE);
        when(req.getParameter("page")).thenReturn("1");
        when(req.getParameter("pageSize")).thenReturn("25");
        when(req.getParameter("suit")).thenReturn(Suit.DIAMONDS.name());
        when(req.getParameter("airDate")).thenReturn("2010-08-01 20:00");

        Vector<String> headerNames = new Vector<String>();
        headerNames.add("Cache-Control");
        when(req.getHeaderNames()).thenReturn(headerNames.elements());
        when(req.getHeader("Cache-Control")).thenReturn("no-cache");

        Cookie[] cookies = new Cookie[]{new Cookie("chocolate", "chip")};
        when(req.getCookies()).thenReturn(cookies);

        return req;
    }
}
