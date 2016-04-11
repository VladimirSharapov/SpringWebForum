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
package org.shv.webforum.service;

import org.shv.webforum.model.entity.Post;
import org.shv.webforum.model.entity.Topic;
import org.shv.webforum.service.common.BaseEntityService;

import org.springframework.data.domain.Page;


/**
 * @author Vladimir Sharapov
 */
public interface PostService extends BaseEntityService<Post> {

    /**
     * Fetch posts for given topic and page
     *
     * @param topic topic to which fetched posts belong
     * @param page  page number
     * @return page object which contains post list
     */
    Page<Post> getPostsInTopic(Topic topic, int page);

    /**
     * Adds post to topic
     *
     * @param submittedPost post entity that contains message
     * @param topic topic inside which post will be created
     */
    void addPostToTopic(Post submittedPost, Topic topic);

    /**
     * Calculate post page number inside topic
     *
     * @param post post entity for which page number is calculated
     * @return page number
     */
    int calculatePostPage(Post post);
}
