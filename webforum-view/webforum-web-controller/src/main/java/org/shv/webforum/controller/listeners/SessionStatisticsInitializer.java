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
package org.shv.webforum.controller.listeners;

import org.shv.webforum.controller.util.ForumStatisticsProvider;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Servlet context listener that performs initialization of
 * {@link org.shv.webforum.controller.util.ForumStatisticsProvider#TOTAL_ACTIVE_SESSIONS}
 *  attribute
 *
 * @author Vladimir Sharapov
 */
public class SessionStatisticsInitializer implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        servletContext.setAttribute(ForumStatisticsProvider.TOTAL_ACTIVE_SESSIONS, new AtomicLong(0));
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
