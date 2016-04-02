/**
 * This project is a simple web forum. I created it just to
 * demonstrate my programming skills to potential employers.
 *
 * Here is short description: ( for more detailed description please reade README.md or
 * go to https://github.com/VladimirSharapov/SpringWebForum )
 *
 * Front-end: jsp, bootstrap, jquery
 * Back-end: Spring, Hibernate
 * DB: MySQL and H2(for testing) were used while developing, but the project is database independent.
 *     Though it must be a relational DB.
 * Tools: git,maven,jenkins,nexus,liquibase.
 *
 * My LinkedIn profile: https://ru.linkedin.com/in/vladimir-sharapov-6075207
 */
package org.shv.webforum.controller.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Vladimir Sharapov
 */
public class RequestLoggingFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(RequestLoggingFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        StringBuffer url = ((HttpServletRequest)servletRequest).getRequestURL();
        String uri = ((HttpServletRequest)servletRequest).getRequestURI();

        logger.debug("RequestLoggingFilter :: url : " + url + " " + "uri: " + uri);
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (Exception ex) {
            logger.debug("RequestLoggingFilterException",ex);
            String context = ((HttpServletRequest)servletRequest).getContextPath();
            ((HttpServletResponse)servletResponse).sendRedirect(context + "/sections");
        }
    }

    @Override
    public void destroy() {

    }
}
