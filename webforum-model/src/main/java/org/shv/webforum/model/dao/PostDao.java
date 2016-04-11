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
package org.shv.webforum.model.dao;

import org.shv.webforum.common.Crud;
import org.shv.webforum.model.entity.Branch;
import org.shv.webforum.model.entity.Post;
import org.shv.webforum.model.entity.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * DAO interface for {@link org.shv.webforum.model.entity.Post} entity
 *
 * @author Vladimir Sharapov
 */
public interface PostDao extends Crud<Post> {

    /**
     * Calculates number of posts inside branch
     *
     * @param branch branch for which post count is calculated
     * @return number of posts inside the branch
     */
    public int getPostCountInBranch(Branch branch);

    /**
     * Finds and returns all posts inside a branch
     *
     * @param branch branch in which we are looking for posts
     * @return all posts that belong to provided branch
     */
    public List<Post> getPostsInBranch(Branch branch);

    /**
     * Retrieve posts for certain topic and page
     *
     * @param topic         topic that contains posts
     * @param pageRequest   object that contains information about page size and offset
     * @return              Page object that contains list of posts
     */
    Page<Post> getPostsInTopic(Topic topic, PageRequest pageRequest);


    /**
     * Finds last post for each topic in input list
     *
     * @param topicList  list of topics for which to find last post
     * @return list of posts, each of which is the last one inside topic
     */
    public List<Post> getLastPosts(List<Topic> topicList);
}
