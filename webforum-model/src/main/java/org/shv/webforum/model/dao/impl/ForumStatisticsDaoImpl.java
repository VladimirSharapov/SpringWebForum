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

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.shv.webforum.model.dao.ForumStatisticsDao;
import org.springframework.stereotype.Repository;

/**
 * DAO class implementing {@link org.shv.webforum.model.dao.ForumStatisticsDao} interface.
 * Contains implementation of methods that provide forum statistics information, such as
 * overall number of registered users and overall number of posts in forum.
 *
 * @author Vladimir Sharapov
 */
@Repository
public class ForumStatisticsDaoImpl  implements ForumStatisticsDao {

    private SessionFactory sessionFactory;

    public ForumStatisticsDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session session() {
        return sessionFactory.getCurrentSession();
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public long getAllPostsCount() {
        return ((Number) session().createQuery("select count(p) from Post p").uniqueResult()).longValue();
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public long getAllUsersCount() {
        return ((Number) session().createQuery("select count(u) from User u").uniqueResult()).longValue();
    }
}
