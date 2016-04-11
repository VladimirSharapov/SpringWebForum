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
package org.shv.webforum.service.jmx;

import java.util.List;


/**
 * Taken from <a href="http://www.sureshpw.com/2012/04/dynamic-logging-with-log4j.html">here</a>, used to expose Log4j
 * configuration via JMX so that we can change it during runtime without restarting the app.
 *
 * @author Vladimir Sharapov
 */
public interface Log4jConfiguratorMXBean {

    /**
     * Lists all the loggers defined in logger configuration. Only these names can be used to change the logging level.
     *
     * @return the list of logger names defined in log4j configuration
     */
    List<String> getLoggers();

    /**
     * Get the level of the logger (INFO, DEBUG, ERROR, etc.). If you don't know the exact logger name, use {@link
     * #getLoggers()} to list all of them.
     *
     * @param logger the name of the logger (from {@link #getLoggers()} to get its current level
     * @return the level specified logger uses or {@code "unavailable"} if such logger does not exist
     */
    String getLogLevel(String logger);

    /**
     * Change the level of the logger in the runtime if you need to see more (or less) information.
     *
     * @param logger the name of the logger to change its level
     * @param level  the level to change logging to, results in no-op if it's the same as the current one. If a logger
     *               with the specified name does not exist, it also results in no-op.
     * @see #getLoggers()
     */
    void setLogLevel(String logger, String level);
}

