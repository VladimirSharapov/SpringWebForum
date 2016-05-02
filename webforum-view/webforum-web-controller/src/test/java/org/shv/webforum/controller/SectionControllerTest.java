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
package org.shv.webforum.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import org.shv.webforum.controller.util.ForumStatisticsProvider;
import org.shv.webforum.model.entity.Section;
import org.shv.webforum.service.SectionService;
import org.shv.webforum.service.common.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vladimir Sharapov
 */
public class SectionControllerTest {

    @Mock
    ForumStatisticsProvider forumStatisticsProvider;

    @Mock
    SectionService sectionService;

    MockMvc mockMvc;

    @Before
    public void setUp() {
        initMocks(this);
        mockMvc = standaloneSetup(new SectionController(sectionService, forumStatisticsProvider)).build();
    }

    @Test
    public void testShowSections() throws Exception {
        long expectedPostsCount = 1;
        long expectedUsersCount = 2;
        long expectedOnlineUsersCount = 3;
        long expectedRegUsersCount = 4;
        long expectedAnonymousCount = 5;

        List<Section> sectionList = new ArrayList<>();

        when(sectionService.getAll()).thenReturn(sectionList);
        when(forumStatisticsProvider.getAllPostsCount()).thenReturn(expectedPostsCount);
        when(forumStatisticsProvider.getAllUsersCount()).thenReturn(expectedUsersCount);
        when(forumStatisticsProvider.getOnlineUsersCount()).thenReturn(expectedOnlineUsersCount);
        when(forumStatisticsProvider.getOnlineRegisteredUsersCount()).thenReturn(expectedRegUsersCount);
        when(forumStatisticsProvider.getOnlineAnonymousUsersCount()).thenReturn(expectedAnonymousCount);

        mockMvc.perform(get("/sections"))
                .andExpect(status().isOk())
                .andExpect(view().name(SectionController.SECTION_LIST_VIEW))
                .andExpect(model().attribute(SectionController.SECTION_LIST_ATTR,sectionList))
                .andExpect(model().attribute(SectionController.SHOW_FORUM_STATS_ATTR,true))
                .andExpect(model().attribute(SectionController.POST_COUNT_ATTR, expectedPostsCount))
                .andExpect(model().attribute(SectionController.USERS_COUNT_ATTR, expectedUsersCount))
                .andExpect(model().attribute(SectionController.ONLINE_USERS_COUNT_ATTR, expectedOnlineUsersCount))
                .andExpect(model().attribute(SectionController.REG_USERS_COUNT_ATTR, expectedRegUsersCount))
                .andExpect(model().attribute(SectionController.ANONYMOUS_ATTR, expectedAnonymousCount));

        verify(sectionService,times(1)).getAll();
        verify(sectionService,times(1)).prepareSectionsForView(sectionList);

        verify(forumStatisticsProvider,times(1)).getAllPostsCount();
        verify(forumStatisticsProvider,times(1)).getAllUsersCount();
        verify(forumStatisticsProvider,times(1)).getOnlineUsersCount();
        verify(forumStatisticsProvider,times(1)).getOnlineRegisteredUsersCount();
        verify(forumStatisticsProvider,times(1)).getOnlineAnonymousUsersCount();
    }

    @Test
    public void testShowSection() throws Exception {
        Long sectionId = 1L;
        Section section = new Section("name","description");
        List<Section> sectionList = new ArrayList<>();
        sectionList.add(section);

        when(sectionService.get(sectionId)).thenReturn(section);

        mockMvc.perform(get("/sections/{sectionId}",sectionId))
                .andExpect(status().isOk())
                .andExpect(view().name(SectionController.SECTION_LIST_VIEW))
                .andExpect(model().attribute(SectionController.SECTION_LIST_ATTR,sectionList));

        verify(sectionService,times(1)).get(sectionId);
        verify(sectionService,times(1)).prepareSectionsForView(sectionList);
    }

    @Test
    public void testShowSectionNotFoundException() throws Exception {
        Long sectionId = 1L;
        Class exClass = null;

        when(sectionService.get(sectionId)).thenThrow(new NotFoundException());
        try {
            mockMvc.perform(get("/sections/{sectionId}",sectionId)).andExpect(status().isOk());
        } catch(Exception ex) {
             exClass = ex.getCause().getClass();
        }

        assertSame(NotFoundException.class, exClass);
    }

}
