package org.shv.webforum.model.entity;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.shv.webforum.common.BaseEntity;

import javax.persistence.*;

/**
 * Stores information about post that user makes
 *
 * @author Vladimir Sharapov
 */
@Entity
public class Post extends BaseEntity {

    private String postContent;

    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime creationDate;

    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime modificationDate;

    @ManyToOne
    @JoinColumn(name="USER")
    private User userCreated;

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
}
