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

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.shv.webforum.model.dao.ForumStatisticsDao;
import org.shv.webforum.service.transactional.ForumStatisticsService;


import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author Vladimir Sharapov
 */
public class ForumStatisticsServiceTest {

    @Mock
    private ForumStatisticsDao forumStatisticsDao;

    private ForumStatisticsService forumStatisticsService;

    @Before
    public void setUp() {
        initMocks(this);
        forumStatisticsService = new ForumStatisticsService(forumStatisticsDao);
    }

    @Test
    public void testGetAllPostsCount() {
        long expectedPostsCount = 5;
        when(forumStatisticsDao.getAllPostsCount()).thenReturn(expectedPostsCount);

        long postsCount = forumStatisticsService.getAllPostsCount();

        verify(forumStatisticsDao).getAllPostsCount();
        assertEquals(expectedPostsCount,postsCount);
    }

    @Test
    public void testGetAllUsersCount() {
        long expectedUsersCount = 5;
        when(forumStatisticsDao.getAllUsersCount()).thenReturn(expectedUsersCount);

        long usersCount = forumStatisticsService.getAllUsersCount();

        verify(forumStatisticsDao).getAllUsersCount();
        assertEquals(expectedUsersCount,usersCount);
    }

}
