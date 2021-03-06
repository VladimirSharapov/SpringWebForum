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

/**
 * DAO interface to receive forums statistics information, such as overall number of posts
 * and registered users
 *
 * @author Vladimir Sharapov
 * @see org.shv.webforum.model.dao.impl.ForumStatisticsDaoImpl
 */
public interface ForumStatisticsDao {

    /**
     * @return number of all posts in forum
     */
    public long getAllPostsCount();

    /**
     * @return number of all users registered in forum
     */
    public long getAllUsersCount();
}
