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

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Custom session listener implementation to track active user sessions
 *
 * @author Vladimir Sharapov
 */
public class SessionStatisticListener implements HttpSessionListener {

    /**
     * {@inheritDoc}
     */
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        AtomicLong activeSessions = (AtomicLong) se.getSession().getServletContext().getAttribute(ForumStatisticsProvider.TOTAL_ACTIVE_SESSIONS);
        activeSessions.incrementAndGet();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void sessionDestroyed(HttpSessionEvent se) {

        AtomicLong activeSessions = (AtomicLong) se.getSession().getServletContext().getAttribute(ForumStatisticsProvider.TOTAL_ACTIVE_SESSIONS);

        /**
         * Tomcat may not invalidate HTTP session on server restart while counter variable
         * will be set to 0 on class reload. So we can quickly get our session count negative when
         * persisted sessions will expire. This check provides us with a self-correcting facility
         * to overcome this problem
         **/
        if (activeSessions.get() > 0) {
            activeSessions.decrementAndGet();
        }
    }
}
