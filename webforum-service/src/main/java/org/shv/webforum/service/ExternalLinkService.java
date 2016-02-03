package org.shv.webforum.service;

import org.shv.webforum.model.entity.ExternalLink;
import org.shv.webforum.service.common.BaseEntityService;

import java.util.List;

/**
 * Provides CRUD operations for {@link ExternalLink}
 *
 * @author Vladimir Sharapov
 */
public interface ExternalLinkService extends BaseEntityService<ExternalLink> {

    /**
     * Return list of all existing external link.
     *
     * @return list of all existing external link.
     */
    List<ExternalLink> getLinks();

    /**
     * Persists link to db.
     *
     * @param link link to persist
     */
    void saveLink(ExternalLink link);


    /**
     * Deletes link with specified id, does nothing if there is no such link.
     *
     * @param id link id to remove
     * @return {@code true} if entity deleted successfully
     */
    boolean deleteLink(long id);
}
