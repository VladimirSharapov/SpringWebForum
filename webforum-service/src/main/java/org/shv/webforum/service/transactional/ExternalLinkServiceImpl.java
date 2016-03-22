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
package org.shv.webforum.service.transactional;

import org.shv.webforum.model.dao.ExternalLinkDao;
import org.shv.webforum.model.entity.ExternalLink;
import org.shv.webforum.service.ExternalLinkService;
import org.shv.webforum.service.common.BaseEntityServiceImpl;

import java.util.List;


/**
 * @author Vladimir Sharapov
 */
public class ExternalLinkServiceImpl extends BaseEntityServiceImpl<ExternalLink, ExternalLinkDao> implements ExternalLinkService {

    public ExternalLinkServiceImpl(ExternalLinkDao dao) {
        super(dao);
    }

    /*
     * {@inheritDoc}
     */
    @Override
    public List<ExternalLink> getLinks() {
        return getDao().getAll();
    }

    /*
     * @inheritDoc
     */
    @Override
    public void saveLink(ExternalLink link) {
        getDao().saveOrUpdate(link);
    }

    /*
     * @inheritDoc
     */
    @Override
    public boolean deleteLink(long id) {
        return getDao().delete(id);
    }
}
