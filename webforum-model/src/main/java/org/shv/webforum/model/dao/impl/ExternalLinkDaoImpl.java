package org.shv.webforum.model.dao.impl;

import org.hibernate.SessionFactory;
import org.shv.webforum.common.GenericDao;
import org.shv.webforum.model.dao.ExternalLinkDao;
import org.shv.webforum.model.entity.ExternalLink;
import org.springframework.stereotype.Repository;

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
    public List<ExternalLink> getAll() {

        return session().createQuery("from ExternalLink").list();
    }
}
