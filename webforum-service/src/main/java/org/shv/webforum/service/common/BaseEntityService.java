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
package org.shv.webforum.service.common;

import org.shv.webforum.common.BaseEntity;


/**
 * This is generic interface for services which would interact with database entities via DAO object.
 *
 * @author Vladimir Sharapov
 */
public interface BaseEntityService<T extends BaseEntity> {

    /**
     * Get persistent object by id.
     *
     * @param id primary id of persistent object to find. If id is less than 0, then IllegalArgumentException will be thrown.
     * @return persistent object T or throw NotFoundException if object is not found.
     */
    T get(Long id) throws NotFoundException;
}
