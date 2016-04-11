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
import org.shv.webforum.model.dao.PostDao;
import org.shv.webforum.model.entity.Branch;
import org.shv.webforum.model.entity.Post;
import org.shv.webforum.model.entity.Topic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Vladimir Sharapov
 */
public class PostDaoImpl extends GenericDao<Post> implements PostDao {

    public PostDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory, Post.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public int getPostCountInBranch(Branch branch) {
        Number count = (Number) session().getNamedQuery("postCountInBranch")
                .setParameter("branch",branch).uniqueResult();

        return count.intValue();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Post> getPostsInBranch(Branch branch) {
        return session().getNamedQuery("postsInBranch").setParameter("branch",branch).list();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public Page<Post> getPostsInTopic(Topic topic, PageRequest pageRequest) {
        Query query =  session().getNamedQuery("postsInTopic");
        query.setParameter("topic",topic);
        query.setFirstResult(pageRequest.getOffset());
        query.setMaxResults(pageRequest.getPageSize());

        int totalPostsCount = getPostsCount(topic);

        return new PageImpl<Post>(query.list(),pageRequest,totalPostsCount);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Post> getLastPosts(List<Topic> topicList) {
        Query query = session().getNamedQuery("lastPosts");
        query.setParameterList("topicList", topicList);

        return query.list();
    }

    @SuppressWarnings("unchecked")
    private int getPostsCount(Topic topic) {
        return ((Number)session().createQuery("select count(p) from Post p where p.topic = :topic")
                .setParameter("topic",topic).uniqueResult())
                .intValue();
    }
}
