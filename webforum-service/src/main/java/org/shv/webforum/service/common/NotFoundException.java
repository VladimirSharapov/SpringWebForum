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
