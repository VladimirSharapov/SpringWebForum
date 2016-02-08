package org.shv.webforum.model.entity;

import org.shv.webforum.common.BaseEntity;

/**
 * Stores information about web forum user.
 *
 * @author Vladimir Sharapov
 */
public class User extends BaseEntity {

    long postCount;

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public long getPostCount() {
        return postCount;
    }

    public void setPostCount(long postCount) {
        this.postCount = postCount;
    }
}
