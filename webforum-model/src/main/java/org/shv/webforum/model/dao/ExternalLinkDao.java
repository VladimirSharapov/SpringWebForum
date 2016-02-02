package org.shv.webforum.model.dao;

import org.shv.webforum.common.Crud;
import org.shv.webforum.model.entity.ExternalLink;

import java.util.List;

/**
 *
 * @author Vladimir Sharapov
 */
public interface ExternalLinkDao extends Crud<ExternalLink> {


    /**
     * Provide a list of all links to external resources.
     *
     * @return list of all links to external resources.
     */
    public List<ExternalLink> getAll();
}
