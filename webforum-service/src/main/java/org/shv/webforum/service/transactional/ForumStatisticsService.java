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
package org.shv.webforum.service.transactional;

import org.shv.webforum.model.dao.ForumStatisticsDao;

/**
 * This class contains methods for getting forum statistic information.
 *
 * @author Vladimir Sharapov
 */
public class ForumStatisticsService  {

    private ForumStatisticsDao statisticsDao;

    public ForumStatisticsService(ForumStatisticsDao statisticsDao) {
        this.statisticsDao = statisticsDao;
    }

    /**
     * Get total number of posts in the forum
     *
     * @return number of posts in the forum.
     */
    public long getAllPostsCount() {
        return statisticsDao.getAllPostsCount();
    }

    /**
     * Return total number of registered user's accounts
     *
     * @return number of registered user's accounts
     */
    public long getAllUsersCount() {
        return statisticsDao.getAllUsersCount();
    }
}
