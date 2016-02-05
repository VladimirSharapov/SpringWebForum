package org.shv.webforum.model.entity;

import org.shv.webforum.common.BaseEntity;

/**
 * Stores information about web forum user.
 *
 * @author Vladimir Sharapov
 */
public class User extends BaseEntity {

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
