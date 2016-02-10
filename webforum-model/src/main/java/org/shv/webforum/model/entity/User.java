package org.shv.webforum.model.entity;

import org.joda.time.DateTime;
import org.shv.webforum.common.BaseEntity;

/**
 * Stores information about web forum user.
 *
 * @author Vladimir Sharapov
 */
public class User extends BaseEntity {




    private String username;

    private String firstName;

    private String lastName;

    private String email;

    DateTime registrationDate;

    long postCount;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public DateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(DateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public long getPostCount() {
        return postCount;
    }

    public void setPostCount(long postCount) {
        this.postCount = postCount;
    }
}
