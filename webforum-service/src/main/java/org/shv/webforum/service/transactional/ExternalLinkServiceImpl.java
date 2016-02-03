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
