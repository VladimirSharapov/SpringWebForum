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

import org.shv.webforum.model.entity.Section;
import org.shv.webforum.service.common.BaseEntityService;

import java.util.List;


/**
 * @author Vladimir Sharapov
 */
public interface SectionService extends BaseEntityService<Section> {

    /**
     * @return list of all sections in forum
     */
    public List<Section> getAll();

    /**
     * Fills topic count and post count for each branch in each forum section inside input list
     *
     * @param sectionList list of forum sections with filled topic and post count
     */
    public void prepareSectionsForView(List<Section> sectionList);
}
