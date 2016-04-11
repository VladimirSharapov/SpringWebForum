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
import org.shv.webforum.model.validation.annotation.NotBlankSized;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
import java.util.ArrayList;
import java.util.List;


/**
 * Stores information about forum topic. Each topic has title and topic starter.
 * Each topic contains posts and belongs to branch.
 *
 * @author Vladimir Sharapov
 */
@NamedQueries({
        @NamedQuery(name="topicsInBranch",      query = "select t from Topic t join fetch t.topicStarter where t.branch = :branch order by t.creationDate desc"),
        @NamedQuery(name="topicsCountInBranch", query = "select count(t) from Topic t where t.branch = :branch")
})
@Entity
public class Topic extends BaseEntity {

    public static final int MIN_TITLE_SIZE = 1;
    public static final int MAX_TITLE_SIZE = 120;

    @NotBlankSized(groups = {Default.class, FormData.class}, min = MIN_TITLE_SIZE, max = MAX_TITLE_SIZE, message = "{javax.validation.constraints.Size.message}")
    private String title;

    @NotNull
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime creationDate;

    // we don't want to fetch topicStarter in postsInBranch query. When we need it, we use join fetch, like in topicsInBranch
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="USER")
    private User topicStarter;

    // we don't want to fetch it in topicsInBranch query
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="BRANCH")
    private Branch branch;

    // we want to fetch all posts at once, so that we don't have n+1 problem when we display topic list
    // ( for each topic we need to provide last post and number of posts inside a topic )
    // this is probably not the best way to solve the task, because we still load all posts, though
    // we don't need them all (just the last one and number of posts). On the other side this is still
    // a good optimization by the price of adding only BatchSize annotation.
    @org.hibernate.annotations.BatchSize(size = 10)
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
     * Set list of posts inside current topic
     * @param posts list of posts to be set
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
        return (posts.size() >= 1) ? posts.get(posts.size() - 1) : null;
    }


    /**
     * Get count of post in topic.
     *
     * @return count of post
     */
    public int getPostCount() {
        return posts.size();
    }

    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) {
            return false;
        }

        Topic rhs = (Topic) obj;
        return new EqualsBuilder()
                .append(creationDate,rhs.creationDate)
                .append(title, rhs.title)
                .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(creationDate)
                .append(title)
                .toHashCode();
    }
}
