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

import org.shv.webforum.model.entity.Branch;
import org.shv.webforum.model.entity.Post;
import org.shv.webforum.model.entity.Topic;
import org.shv.webforum.service.common.BaseEntityService;

import org.springframework.data.domain.Page;

import java.util.List;


/**
 * @author Vladimir Sharapov
 */
public interface TopicService extends BaseEntityService<Topic> {

    /**
     * Finds topic for given branch and page
     *
     * @param branch   topics that belong to this branch are fetched
     * @param page     page number
     * @return topics for input branch and page
     */
    public Page<Topic> fetchTopics(Branch branch, int page);

    /**
     * Creates topic and post within created topic
     *
     * @param branch   branch to which created topic will belong
     * @param topic    topic to be created - contains only topic message
     * @param post     post to be created - contains only post message
     * @return created topic with filled creation date, branch and topicStarter
     */
    public Topic createTopicWithPost(Branch branch,Topic topic, Post post);

    /**
     * @return all topics on forum
     */
    public List<Topic> getAllTopics();
}
