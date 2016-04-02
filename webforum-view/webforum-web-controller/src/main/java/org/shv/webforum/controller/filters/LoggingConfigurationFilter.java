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

import org.shv.webforum.service.SecurityService;
import org.slf4j.MDC;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.servlet.*;
import java.io.IOException;

/**
 * This filter binds a current user to the thread therefore allowing to show her username in each line of logs (which is
 * helpful when it comes to combining several actions of user and looking after the logs). It's implemented by using
 * features like {@link org.slf4j.MDC}, we need to register a username in the beginning and then unregister it in the
 * end of the request so that the memory doesn't leak.
 * <p>See logger configuration to see where the username is going to appear, for instance in log4j it may look like {@code
 * %X{userName}}.</p>
 *
 * @author Vladimir Sharapov
 */
public class LoggingConfigurationFilter implements Filter {

    //must be the same as in logging pattern
    private static final String USER_NAME_KEY = "userName";

    private final SecurityService securityService;

    /**
     * @param securityService to get current user for registration in logging context
     */
    public LoggingConfigurationFilter(SecurityService securityService) {
        this.securityService = securityService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String currentUserName = securityService.getCurrentUserUsername();

        registerUserName(currentUserName);
        try {
            chain.doFilter(request, response);
        } finally {
            MDC.remove(USER_NAME_KEY);
        }
    }

    private void registerUserName(String userName) {
        if(userName != null) {
            MDC.put(USER_NAME_KEY, userName);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void destroy() {
        //empty
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //empty
    }

}
