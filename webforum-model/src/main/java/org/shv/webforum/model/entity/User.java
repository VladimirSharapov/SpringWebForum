package org.shv.webforum.model.entity;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.joda.time.DateTime;
import org.shv.webforum.common.BaseEntity;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

/**
 * Stores information about web forum user.
 *
 * @author Vladimir Sharapov
 */
@Entity
public class User extends BaseEntity {

    private static final String USER_EMAIL_ILLEGAL_FORMAT = "{validation.invalid_email_format}";
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
    public static final int USERNAME_FIRSTNAME_MIN_LENGTH = 0;
    public static final int USERNAME_FIRSTNAME_MAX_LENGTH = 45;
    public static final int USERNAME_LASTNAME_MIN_LENGTH = 0;
    public static final int USERNAME_LASTNAME_MAX_LENGTH = 255;
    public static final int EMAIL_MAX_LENGTH = 50;


    @NotNull(message = USER_CANT_BE_NULL)
    @Length(min = USERNAME_MIN_LENGTH, max = USERNAME_MAX_LENGTH, message = USER_USERNAME_ILLEGAL_LENGTH)
    private String username;

    @NotBlank
    @Length(min = PASSWORD_MIN_LENGTH, max = PASSWORD_MAX_LENGTH, message = USER_PASSWORD_ILLEGAL_LENGTH)
    private String password;

    @Length(min = USERNAME_FIRSTNAME_MIN_LENGTH, max = USERNAME_FIRSTNAME_MAX_LENGTH, message = USER_FIRST_NAME_ILLEGAL_LENGTH)
    private String firstName;

    @Length(min = USERNAME_LASTNAME_MIN_LENGTH, max = USERNAME_LASTNAME_MAX_LENGTH, message = USER_LAST_NAME_ILLEGAL_LENGTH)
    private String lastName;

    @Email(message = USER_EMAIL_ILLEGAL_FORMAT)
    @Length(max = EMAIL_MAX_LENGTH, message = EMAIL_ILLEGAL_LENGTH)
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

    /**
     * @param username name that identifies web forum user
     */
    public void setUsername(String username) {
        this.username = username;
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
}
