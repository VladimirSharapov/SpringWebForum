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
package org.shv.webforum.controller.util;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.shv.webforum.service.transactional.ForumStatisticsService;
import org.springframework.security.core.session.SessionRegistry;


import javax.servlet.ServletContext;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author Vladimir Sharapov
 */
public class ForumStatisticsProviderTest {

    @Mock
    private ServletContext servletContext;

    @Mock
    private SessionRegistry sessionRegistry;

    @Mock
    private ForumStatisticsService forumStatisticsService;

    private ForumStatisticsProvider forumStatisticsProvider;

    @Before
    public void setUp() {
        initMocks(this);
        forumStatisticsProvider = new ForumStatisticsProvider(sessionRegistry,forumStatisticsService);
        forumStatisticsProvider.setServletContext(servletContext);
    }

    @Test
    public void testAllPostsCount() {
        long expectedPostsCount = 6;
        when(forumStatisticsService.getAllPostsCount()).thenReturn(expectedPostsCount);

        long postsCount = forumStatisticsProvider.getAllPostsCount();

        verify(forumStatisticsService).getAllPostsCount();
        assertEquals(expectedPostsCount,postsCount);
    }

    @Test
    public void testGetAllUsersCount() {
        long expectedUsersCount = 6;
        when(forumStatisticsService.getAllUsersCount()).thenReturn(expectedUsersCount);

        long usersCount = forumStatisticsProvider.getAllUsersCount();

        verify(forumStatisticsService).getAllUsersCount();
        assertEquals(expectedUsersCount,usersCount);
    }

    @Test
    public void testGetOnlineUsersCount() {
        long expectedNumberOfActiveSessions = 10;
        when(servletContext.getAttribute(ForumStatisticsProvider.TOTAL_ACTIVE_SESSIONS)).
                thenReturn(new AtomicLong(expectedNumberOfActiveSessions));

        long onlineUsersCount = forumStatisticsProvider.getOnlineUsersCount();

        assertEquals(expectedNumberOfActiveSessions, onlineUsersCount);
    }

    @Test
    public void testGetOnlineRegisteredUsersCount() {
        when(sessionRegistry.getAllPrincipals()).thenReturn(Arrays.asList(new Object(), new Object()));

        long regUsersCount = forumStatisticsProvider.getOnlineRegisteredUsersCount();

        verify(sessionRegistry).getAllPrincipals();
        assertEquals(2, regUsersCount);
    }

    @Test
    public void testGetOnlineAnonymousUsersCount() {
        long expectedNumberOfActiveSessions = 10;
        when(sessionRegistry.getAllPrincipals()).thenReturn(Arrays.asList(new Object(), new Object()));
        when(servletContext.getAttribute(ForumStatisticsProvider.TOTAL_ACTIVE_SESSIONS)).
                thenReturn(new AtomicLong(expectedNumberOfActiveSessions));

        long anonymousUsersCount = forumStatisticsProvider.getOnlineAnonymousUsersCount();

        assertEquals(expectedNumberOfActiveSessions-2,anonymousUsersCount);
    }
}
