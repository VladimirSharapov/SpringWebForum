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
package org.shv.webforum.service.common;

/**
 * This exception is thrown when searched item is not found.
 *
 * @author Vladimir Sharapov
 */
public class NotFoundException extends Exception {

    /**
     * Default constructor
     */
    public NotFoundException() {
    }

    /**
     * Create exception with specific message.
     *
     * @param message exception message
     */
    public NotFoundException(String message) {
        super(message);
    }
}
