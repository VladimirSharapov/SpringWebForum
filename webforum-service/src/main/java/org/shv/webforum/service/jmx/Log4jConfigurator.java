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

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Allows exposing Log4j operations/attributes via JMX so that we can change the logging level in runtime.<br>
 * Taken from <a href="http://www.sureshpw.com/2012/04/dynamic-logging-with-log4j.html">here</a>.
 *
 * @author Vladimir Sharapov
 */
public class Log4jConfigurator implements Log4jConfiguratorMXBean {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getLoggers() {
        List<String> list = new ArrayList<String>();
        for (Logger log : getAllLoggers()) {
            if (log.getLevel() != null) {
                list.add(log.getName() + " = " + log.getLevel().toString());
            }
        }
        return list;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getLogLevel(String logger) {
        String level = "unavailable";
        if (StringUtils.isNotBlank(logger)) {
            Logger log = getLogger(logger);
            if (log != null) {
                level = log.getLevel().toString();
            }
        }
        return level;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLogLevel(String logger, String level) {
        if (StringUtils.isNotBlank(logger) && StringUtils.isNotBlank(level)) {
            Logger log = getLogger(logger);
            if (log != null) {
                log.setLevel(Level.toLevel(level.toUpperCase()));
            }
        }
    }

    List<Logger> getAllLoggers() {
        return Collections.<Logger>list(LogManager.getCurrentLoggers());
    }

    Logger getLogger(String logger) {
        return Logger.getLogger(logger);
    }
}
