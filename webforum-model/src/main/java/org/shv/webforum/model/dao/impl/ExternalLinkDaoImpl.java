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
import org.shv.webforum.model.dao.ExternalLinkDao;
import org.shv.webforum.model.entity.ExternalLink;
import org.springframework.stereotype.Repository;

import org.hibernate.SessionFactory;

import java.util.List;


/**
 *
 * @author Vladimir Sharapov
 */
@Repository
public class ExternalLinkDaoImpl extends GenericDao<ExternalLink> implements ExternalLinkDao {

    /**
     *
     * @param sessionFactory The SessionFactory.
     */
    public ExternalLinkDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory, ExternalLink.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<ExternalLink> getAll() {
        return session().createQuery("select e from ExternalLink e").list();
    }
}
