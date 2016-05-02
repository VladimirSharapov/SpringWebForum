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

import org.shv.webforum.model.entity.Post;
import org.shv.webforum.model.entity.Topic;
import org.shv.webforum.service.BranchService;
import org.shv.webforum.service.PostService;
import org.shv.webforum.service.TopicService;
import org.shv.webforum.service.UserService;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * @author Vladimir Sharapov
 */
public class TopicControllerTest {

    @Mock
    private BranchService branchService;

    @Mock
    private TopicService topicService;

    @Mock
    private PostService postService;

    @Mock
    private UserService userService;

    MockMvc mockMvc;

    @Before
    public void setUp() {
        initMocks(this);
        mockMvc = standaloneSetup(new TopicController(branchService,topicService,postService,userService)).build();
    }


    @Test
    public void testShowPosts() throws Exception {
        int pageNumber = 1;
        long topicId = 1L;
        long lastPostId = 1L;
        Topic topic = createTopic();
        Page<Post> expectedPage = createPage(1L);

        when(topicService.get(topicId)).thenReturn(topic);
        when(postService.getPostsInTopic(topic,pageNumber)).thenReturn(expectedPage);

        mockMvc.perform(
                get("/topics/{topicId}",topicId).param("page",Integer.toString(pageNumber)))
                .andExpect(status().isOk())
                .andExpect(view().name(TopicController.POST_LIST_VIEW))
                .andExpect(model().attribute(TopicController.TOPIC_ATTR, topic))
                .andExpect(model().attribute(TopicController.POST_PAGE_ATTR,expectedPage))
                .andExpect(model().attribute(TopicController.LAST_POST_ID_ATTR, lastPostId));

        verify(topicService,times(1)).get(topicId);
        verify(postService,times(1)).getPostsInTopic(topic,pageNumber);
    }

    private Page<Post> createPage(long lastPostId) {
        Post post = new Post();
        post.setId(lastPostId);

        List<Post> postList = new ArrayList<Post>();
        postList.add(post);

        return new PageImpl<Post>(postList);
    }

    private Topic createTopic() {
        Topic topic = new Topic();
        topic.setId(1L);
        return topic;
    }

}
