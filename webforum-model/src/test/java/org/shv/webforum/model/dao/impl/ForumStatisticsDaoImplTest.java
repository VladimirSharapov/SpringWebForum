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
package org.shv.webforum.model.dao.impl;

import org.shv.webforum.model.dao.ForumStatisticsDao;
import org.shv.webforum.model.util.JpaEntityState;
import org.shv.webforum.model.util.PersistedObjectsFactory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;


/**
 * @author Vladimir Sharapov
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/applicationContext-dao.xml")
@ActiveProfiles("test")
@Transactional
public class ForumStatisticsDaoImplTest {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private ForumStatisticsDao forumStatisticsDao;

    private Session session;

    @Before
    public void setUp() {
        session = sessionFactory.getCurrentSession();
        PersistedObjectsFactory.setSession(session);
    }

    @Test
    public void testGetAllPostsCount() {
        PersistedObjectsFactory.createPost(JpaEntityState.DETACHED);
        PersistedObjectsFactory.createPost(JpaEntityState.DETACHED);
        PersistedObjectsFactory.createPost(JpaEntityState.DETACHED);

        assertEquals(3, forumStatisticsDao.getAllPostsCount());
    }

    @Test
    public void testGetAllUsersCount() {
        PersistedObjectsFactory.createUser(JpaEntityState.DETACHED);
        PersistedObjectsFactory.createUser(JpaEntityState.DETACHED);

        assertEquals(2, forumStatisticsDao.getAllUsersCount());
    }
}
