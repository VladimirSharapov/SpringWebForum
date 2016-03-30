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
import org.shv.webforum.model.entity.Topic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;


/**
 * DAO interface for {@link org.shv.webforum.model.entity.Topic} entity
 *
 * @author Vladimir Sharapov
 */
public interface TopicDao extends Crud<Topic> {

    /**
     * Fetches list of topics that belong to the branch for given page and page size
     *
     * @param branch for which to fetch topics
     * @param pageRequest  contains pageNumber and pageSize
     * @return  list of topics in branch
     */
    public Page<Topic> getTopicsInBranch(Branch branch, PageRequest pageRequest);

    /**
     * Calculates number of topics inside the branch
     *
     * @param branch for which topics are counted
     * @return number of topics inside branch
     */
    public int getTopicCountInBranch(Branch branch);

    /**
     * @return list of topics that contains all topics on forum
     */
    public List<Topic> getAllTopics();
}
