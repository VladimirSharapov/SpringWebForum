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
package org.shv.webforum.model.dao.impl;

import org.shv.webforum.common.GenericDao;
import org.shv.webforum.model.dao.TopicDao;
import org.shv.webforum.model.entity.Branch;
import org.shv.webforum.model.entity.Topic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import java.util.List;


/**
 * DAO class for performing CRUD operations and queries for {@link org.shv.webforum.model.entity.Topic} entities
 *
 * @author Vladimir Sharapov
 */
public class TopicDaoImpl extends GenericDao<Topic> implements TopicDao {

    public TopicDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory, Topic.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public Page<Topic> getTopicsInBranch(Branch branch, PageRequest pageRequest) {
        Query query = session().getNamedQuery("topicsInBranch").setParameter("branch",branch);

        query.setMaxResults(pageRequest.getPageSize());
        query.setFirstResult(pageRequest.getOffset());

        List<Topic> topicList = query.list();

        int topicCount = getTopicCountInBranch(branch);

        return new PageImpl(topicList,pageRequest,topicCount);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public int getTopicCountInBranch(Branch branch) {
        return ((Number) session().getNamedQuery("topicsCountInBranch")
                .setParameter("branch",branch).uniqueResult()).intValue();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Topic> getAllTopics() {
        return session().createQuery("select t from Topic t").list();
    }
}
