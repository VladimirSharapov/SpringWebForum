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
import org.hibernate.annotations.*;
import org.shv.webforum.common.BaseEntity;
import org.shv.webforum.model.validation.FormData;

import org.joda.time.DateTime;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;


/**
 * Stores information about post that user makes
 *
 * @author Vladimir Sharapov
 */
@NamedQueries({
        @NamedQuery(name= "postsInTopic",       query="select p from Post p join fetch p.userCreated where p.topic = :topic order by p.id"),
        @NamedQuery(name= "lastPosts",          query="select p from Post p join fetch  p.userCreated join p.topic t " +
                                                      "where p.id in ( select max(p1.id) from Post p1 group by p1.topic ) " +
                                                      "and p.topic in :topicList"),
        @NamedQuery(name = "postsInBranch",     query = "select p from Post p join fetch p.topic join fetch p.userCreated where p.topic.branch = :branch"),
        @NamedQuery(name = "postCountInBranch", query = "select COUNT(p) from Post p where p.topic.branch = :branch")
})
@Entity
public class Post extends BaseEntity {

    public static final int MIN_LENGTH = 2;
    public static final int MAX_LENGTH = 1000;

    @Size(groups = {Default.class,FormData.class},min = Post.MIN_LENGTH, max = Post.MAX_LENGTH,  message = "{javax.validation.constraints.Size.message}")
    private String postContent;

    @NotNull
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime creationDate;

    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime modificationDate;

    // we want to keep it eager because while lazy loading posts for topic we also want user to be loaded with post in
    // one trip to database
    @ManyToOne
    @JoinColumn(name="USER")
    private User userCreated;

    // we don't want to fetch topic in postsInTopic query
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="TOPIC")
    private Topic topic;

    /**
     * @return post text content
     */
    public String getPostContent() {
        return postContent;
    }

    /**
     * @param postContent post text content
     */
    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    /**
     * @return post creation date
     */
    public DateTime getCreationDate() {
        return creationDate;
    }

    /**
     * @param creationDate, date when post is created
     */
    public void setCreationDate(DateTime creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * @return post modification date
     */
    public DateTime getModificationDate() {
        return modificationDate;
    }

    /**
     * @param modificationDate, date when post is modified
     */
    public void setModificationDate(DateTime modificationDate) {
        this.modificationDate = modificationDate;
    }

    /**
     * @return user that created post
     */
    public User getUserCreated() {
        return userCreated;
    }

    /**
     * @param userCreated user that created post
     */
    public void setUserCreated(User userCreated) {
        this.userCreated = userCreated;
    }

    /**
     * @return topic to which this post belongs
     */
    public Topic getTopic() {
        return topic;
    }

    /**
     * @param topic topic to which this post belongs
     */
    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) {
            return false;
        }
        Post rhs = (Post) obj;
        return new EqualsBuilder()
                .append(creationDate, rhs.creationDate)
                .append(postContent, rhs.postContent)
                .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(creationDate)
                .append(postContent)
                .toHashCode();
    }
}
