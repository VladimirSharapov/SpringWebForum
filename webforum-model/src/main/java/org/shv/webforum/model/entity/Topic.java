package org.shv.webforum.model.entity;

import org.shv.webforum.common.BaseEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vladimir Sharapov
 */
public class Topic extends BaseEntity {

    private String title;


    private User topicStarter;

    private Branch branch;

    List<Post> posts = new ArrayList<>();

    public String getTitle() {
        return title;
    }

    /**
     * @param newTitle new title for this topic
     */
    public void setTitle(String newTitle) {
        this.title = newTitle;
    }


    /**
     * Get the user who created the post.
     *
     * @return the userCreated
     */
    public User getTopicStarter() {
        return topicStarter;
    }

    /**
     * The the author of the post.
     *
     * @param userCreated the user who create the post
     */
    public void setTopicStarter(User userCreated) {
        this.topicStarter = userCreated;
    }



    public void setPosts(List<Post> posts) {
        this.posts = posts;
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


    /**
     * @return branch that contains the topic
     */
    public Branch getBranch() {
        return branch;
    }

    /**
     * @param branch branch to be set as topics branch
     */
    public void setBranch(Branch branch) {
        this.branch = branch;
    }


}
