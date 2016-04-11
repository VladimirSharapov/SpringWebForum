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
import org.shv.webforum.model.entity.User;


/**
 * DAO interface for {@link org.shv.webforum.model.entity.User} entity
 *
 * @author Vladimir Sharapov
 */
public interface UserDao extends Crud<User> {

    /**
     * Find user by username
     *
     * @param userName  username of user
     * @return User entity
     */
    public User findUserByName(String userName);
}
