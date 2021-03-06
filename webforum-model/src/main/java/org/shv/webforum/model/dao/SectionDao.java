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
package org.shv.webforum.model.dao;

import org.shv.webforum.common.Crud;
import org.shv.webforum.model.entity.Section;

import java.util.List;


/**
 * DAO interface for {@link org.shv.webforum.model.entity.Section} entity
 *
 * @author Vladimir Sharapov
 */
public interface SectionDao extends Crud<Section> {

    /**
     * @return list of all sections in the forum
     */
    public List<Section> getAll();
}
