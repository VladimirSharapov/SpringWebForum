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
package org.shv.webforum.controller.util;

import org.shv.webforum.service.transactional.ForumStatisticsService;

import org.springframework.security.core.session.SessionRegistry;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * This class contains methods for getting and calculate forum statistic information.
 *
 * @author Vladimir Sharapov
 */
public class ForumStatisticsProvider implements ServletContextAware {

    public static final String TOTAL_ACTIVE_SESSIONS = "TOTAL_ACTIVE_SESSIONS";

    private ServletContext servletContext;
    private SessionRegistry sessionRegistry;
    private ForumStatisticsService statisticsService;

    /**
     * Create an instance of forum statistics provider
     *
     * @param sessionRegistry          for operations with data storage
     * @param statisticsService        for getting active users information
     */
    public ForumStatisticsProvider(SessionRegistry sessionRegistry,
                                   ForumStatisticsService statisticsService) {
        this.sessionRegistry          = sessionRegistry;
        this.statisticsService        = statisticsService;
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    /**
     * Get total count of messages on the forum
     *
     * @return number of posts on the forum.
     */
    public long getAllPostsCount() {
        return statisticsService.getAllPostsCount();
    }

    /**
     * Return total count of registered user's accounts
     *
     * @return count of registered user's accounts
     */
    public long getAllUsersCount() {
        return statisticsService.getAllUsersCount();
    }

    /**
     * Return list of registered users who is online now
     *
     * @return list of users
     */
    public List<Object> getOnlineRegisteredUsers() {
        return sessionRegistry.getAllPrincipals();
    }

    /**
     * Return total number of online users
     *
     * @return total number of online users
     */
    public long getOnlineUsersCount() {
        return getTotalActiveSessions();
    }

    /**
     * Return number of online registered users
     *
     * @return number of users
     */
    public long getOnlineRegisteredUsersCount() {
        return sessionRegistry.getAllPrincipals().size();
    }

    /**
     * Return number of online anonymous users
     *
     * @return number of users
     */
    public long getOnlineAnonymousUsersCount() {
       return getTotalActiveSessions() - sessionRegistry.getAllPrincipals().size();
    }

    private long getTotalActiveSessions() {
        return ((AtomicLong) servletContext.getAttribute(TOTAL_ACTIVE_SESSIONS)).longValue();
    }
}
