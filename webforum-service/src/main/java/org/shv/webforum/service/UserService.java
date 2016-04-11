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

import org.shv.webforum.model.entity.User;
import org.shv.webforum.service.common.BaseEntityService;
import org.shv.webforum.service.common.NotFoundException;


/**
 * @author Vladimir Sharapov
 */
public interface UserService extends BaseEntityService<User> {

    /**
     * Finds and returns current user entity
     *
     * @return current user
     */
     User getCurrentUser();

    /**
     * Edit user by merging new user data
     *
     * @param user user entity that contains new user data
     */
    void editUser(User user);

    /**
     * Increases post count for the current user by one. Is called when user creates a post.
     */
    void updatePostCount() throws NotFoundException;
}
