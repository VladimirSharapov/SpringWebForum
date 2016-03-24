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

/**
 * This is a generic AJAX response class to send JSON response from server to the client.
 * Can be used for any generic AJAX interaction between server and client.
 *
 * @author Vladimir Sharapov
 */
public class JsonResponse {

    private JsonResponseStatus status;
    private Object result;

    /**
     * Creates new instance
     *
     * @param status response status
     * @param result data
     */
    public JsonResponse(JsonResponseStatus status, Object result) {
        this.status = status;
        this.result = result;
    }

    /**
     * Creates new instance
     *
     * @param status response status
     */
    public JsonResponse(JsonResponseStatus status) {
        this(status, null);
    }

    /**
     * @return response status
     */
    public JsonResponseStatus getStatus() {
        return status;
    }

    /**
     * @param status response status
     */
    public void setStatus(JsonResponseStatus status) {
        this.status = status;
    }

    /**
     * @return response data
     */
    public Object getResult() {
        return result;
    }

    /**
     * @param result response data
     */
    public void setResult(Object result) {
        this.result = result;
    }
}
