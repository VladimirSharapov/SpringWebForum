package org.shv.webforum.model.entity;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.shv.webforum.common.BaseEntity;
import org.shv.webforum.model.validation.annotation.NotBlankSized;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Stores information about forum topic. Each topic has title and topic starter.
 * Each topic contains posts and belongs to branch.
 *
 * @author Vladimir Sharapov
 */
@Entity
public class Topic extends BaseEntity {

    public static final int MIN_NAME_SIZE = 1;
    public static final int MAX_NAME_SIZE = 120;

    @NotBlankSized(min = MIN_NAME_SIZE, max = MAX_NAME_SIZE, message = "{javax.validation.constraints.Size.message}")
    private String title;

    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime creationDate;

    @ManyToOne
    @JoinColumn(name="USER")
    private User topicStarter;

    @ManyToOne
    @JoinColumn(name="BRANCH")
    private Branch branch;

    @OneToMany(mappedBy="topic")
    private List<Post> posts = new ArrayList<>();


    /**
     * @return topic title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title  for this topic
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return topic creation date
     */
    public DateTime getCreationDate() {
        return creationDate;
    }

    /**
     * @param creationDate topic creation date
     */
    public void setCreationDate(DateTime creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * Get the user who created the topic.
     *
     * @return the user who created the topic
     */
    public User getTopicStarter() {
        return topicStarter;
    }

    /**
     * Set the user who created the topic.
     *
     * @param topicStarter the user who created the topic
     */
    public void setTopicStarter(User topicStarter) {
        this.topicStarter = topicStarter;
    }

    /**
     * @return branch that contains the topic
     */
    public Branch getBranch() {
        return branch;
    }

    /**
     * @param branch to which topic belongs
     */
    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    /**
     * @return list of posts inside current topic
     */
    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }


    /**
     * @return list of posts inside current topic
     */
    public List<Post> getPosts() {
        return posts;
    }

    /**
     * Adds {@link org.shv.webforum.model.entity.Post} to current topic
     * Method also sets topic to the post added
     *
     * @param post that is added to topic
     */
    public void addPost(Post post) {
        post.setTopic(this);
        posts.add(post);
    }

    /**
     * Get the last post in the topic. Topics are guaranteed to have at least the first post.
     *
     * @return last post in the topic.
     */
    public Post getLastPost() {
        return posts.get(posts.size() - 1);
    }


    /**
     * Get count of post in topic.
     *
     * @return count of post
     */
    public int getPostCount() {
        return posts.size();
    }




}
