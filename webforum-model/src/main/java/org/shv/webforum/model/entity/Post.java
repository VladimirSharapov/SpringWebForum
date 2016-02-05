package org.shv.webforum.model.entity;

import org.joda.time.DateTime;
import org.shv.webforum.common.BaseEntity;

/**
 * @author Vladimir Sharapov
 */
public class Post extends BaseEntity {

    public static final String URL_SUFFIX = "/posts/";

    private DateTime creationDate;
    private DateTime modificationDate;
    private User userCreated;
    private String postContent;
    private Topic topic;
    private int rating;


    public DateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(DateTime creationDate) {
        this.creationDate = creationDate;
    }

    public DateTime getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(DateTime modificationDate) {
        this.modificationDate = modificationDate;
    }

    public User getUserCreated() {
        return userCreated;
    }

    public void setUserCreated(User userCreated) {
        this.userCreated = userCreated;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
