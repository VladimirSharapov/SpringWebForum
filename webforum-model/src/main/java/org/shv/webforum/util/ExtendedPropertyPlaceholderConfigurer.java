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
package org.shv.webforum.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;


/**
 *
 * This class extends {@link PropertyPlaceholderConfigurer} and overrides resolvePlaceholder method, so that
 * property value is first looked up in JNDI context of application server and if not found super.resolvePlaceholder method is called.
 * Thus we can store our properties outside war file in $CATALINA_HOME/Catalina/localhost/webforum-config.xml
 *
 * @author Vladimir Sharapov
 */
public class ExtendedPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {


    private static final String TOMCAT_CONTEXT_NAME = "java:/comp/env";

    private final Logger logger = LoggerFactory.getLogger(ExtendedPropertyPlaceholderConfigurer.class);

    /**
     * <p>Looks for Tomcat JNDI environment first to get variables from there and returns if the value was found, if
     * nothing found then it works as usual configurer: </p> {@inheritDoc}
     */
    @Override
    protected String resolvePlaceholder(String placeholder, Properties props, int systemPropertiesMode) {
        String propValue = resolveJndiProperty(placeholder);
        if (propValue == null) {
            propValue = super.resolvePlaceholder(placeholder, props, systemPropertiesMode);
        }
        return propValue;
    }

    /**
     * Takes a look at Tomcat JNDI environment ({@code java:/comp/env}) and tries to find the placeholder there. Returns
     * {@code null} if nothing found there, otherwise found value is returned as a string.
     *
     * @param name the property name to find its value
     * @return the value of the property from Tomcat JNDI or {@code null} if nothing found there
     */
    public String resolveJndiProperty(String name) {
        String propValue = null;
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup(TOMCAT_CONTEXT_NAME);
            propValue = (String) envContext.lookup(name);
            logger.info("Property {} taken from JNDI.", name, propValue);
        } catch (NamingException e) {
            logger.info("Could not resolve JNDI property [{}]. Will be trying file properties, then System ones and " +
                    "if not found anywhere, then defaults will be taken", name);
        }
        return propValue;
    }

}
