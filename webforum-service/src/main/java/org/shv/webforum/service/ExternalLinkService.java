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
