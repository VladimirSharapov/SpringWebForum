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
package org.shv.webforum.model.entity;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.shv.webforum.common.BaseEntity;
import org.shv.webforum.model.validation.FormData;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import org.joda.time.DateTime;

import javax.jws.soap.SOAPBinding;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;
import java.util.Collection;


/**
 * Stores information about web forum user.
 *
 * @author Vladimir Sharapov
 */
@NamedQueries({
        @NamedQuery(name="userByUsername", query = "select u from User u where u.username = :username")
})
@Entity
public class User extends BaseEntity implements UserDetails {

    private static final String USER_PASSWORD_ILLEGAL_LENGTH = "{user.password.length_constraint_violation}";
    private static final String USER_USERNAME_ILLEGAL_LENGTH = "{user.username.length_constraint_violation}";
    private static final String USER_CANT_BE_NULL = "{user.username.null_constraint_violation}";
    private static final String USER_FIRST_NAME_ILLEGAL_LENGTH = "{user.first_name.illegal_length}";
    private static final String USER_LAST_NAME_ILLEGAL_LENGTH = "{user.last_name.illegal_length}";
    private static final String EMAIL_ILLEGAL_LENGTH = "{user.email.illegal_length}";

    public static final int USERNAME_MIN_LENGTH = 1;
    public static final int USERNAME_MAX_LENGTH = 25;
    public static final int PASSWORD_MIN_LENGTH = 1;
    public static final int PASSWORD_MAX_LENGTH = 50;
    public static final int USERNAME_FIRSTNAME_MIN_LENGTH = 1;
    public static final int USERNAME_FIRSTNAME_MAX_LENGTH = 45;
    public static final int USERNAME_LASTNAME_MIN_LENGTH = 1;
    public static final int USERNAME_LASTNAME_MAX_LENGTH = 255;
    public static final int EMAIL_MAX_LENGTH = 50;

    @NotNull(message = USER_CANT_BE_NULL)
    @Size(min = USERNAME_MIN_LENGTH, max = USERNAME_MAX_LENGTH, message = USER_USERNAME_ILLEGAL_LENGTH)
    private String username;

    @NotBlank
    @Size(min = PASSWORD_MIN_LENGTH, max = PASSWORD_MAX_LENGTH, message = USER_PASSWORD_ILLEGAL_LENGTH)
    private String password;

    @Size(groups = {Default.class,FormData.class}, min = USERNAME_FIRSTNAME_MIN_LENGTH, max = USERNAME_FIRSTNAME_MAX_LENGTH, message = USER_FIRST_NAME_ILLEGAL_LENGTH)
    private String firstName;

    @Size(groups = {Default.class,FormData.class}, min = USERNAME_LASTNAME_MIN_LENGTH, max = USERNAME_LASTNAME_MAX_LENGTH, message = USER_LAST_NAME_ILLEGAL_LENGTH)
    private String lastName;

    @Email(groups = {Default.class,FormData.class})
    @Size(groups = {Default.class,FormData.class}, max = EMAIL_MAX_LENGTH, message = EMAIL_ILLEGAL_LENGTH)
    private String email;

    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime registrationDate;

    private long postCount;

    /**
     * @return username, which is web forum user unique identifier
     */
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * @param username name that identifies web forum user
     */
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    /**
     * @return user password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password , user password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return user's first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName user's first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return user's last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName user's last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return user's email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email user's email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return user's registration date
     */
    public DateTime getRegistrationDate() {
        return registrationDate;
    }

    /**
     * @param registrationDate user's registration date
     */
    public void setRegistrationDate(DateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    /**
     * @return number of posts made by this user
     */
    public long getPostCount() {
        return postCount;
    }

    /**
     * @param postCount number of posts made by this user
     */
    public void setPostCount(long postCount) {
        this.postCount = postCount;
    }

    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) {
            return false;
        }
        User rhs = (User) obj;
        return new EqualsBuilder()
                .append(username,rhs.username)
                .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(username)
                .toHashCode();
    }
}
