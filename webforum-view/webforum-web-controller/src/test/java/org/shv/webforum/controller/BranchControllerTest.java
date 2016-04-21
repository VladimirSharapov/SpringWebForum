package org.shv.webforum.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.shv.webforum.model.entity.Branch;
import org.shv.webforum.model.entity.Post;
import org.shv.webforum.model.entity.Topic;
import org.shv.webforum.service.BranchService;
import org.shv.webforum.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.isNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;


/**
 * @author Vladimir Sharapov
 */
public class BranchControllerTest {

    @Autowired
    private WebApplicationContext wac;

    @Mock
    private TopicService topicService;

    @Mock
    private BranchService branchService;

    private MockMvc mockMvc;

    private BranchController branchController;

    @Before
    public void setUp() {
        initMocks(this);
        mockMvc = standaloneSetup(new BranchController(topicService,branchService)).build();
    }

    @Test
    public void testShowTopicsWithDefaultPage() throws Exception {
        Branch branch = new Branch("name","description");
        Page<Topic> expectedPage = new PageImpl<>(new ArrayList<Topic>());
        long branchId = 1;

        when(branchService.get(branchId)).thenReturn(branch);
        when(topicService.fetchTopics(branch,0)).thenReturn(expectedPage);

        mockMvc.perform(get("/branches/" + branchId))
                .andExpect(status().isOk())
                .andExpect(view().name(BranchController.TOPIC_LIST_VIEW))
                .andExpect(model().attribute(BranchController.BRANCH_ATTR, branch))
                .andExpect(model().attribute(BranchController.TOPICS_PAGE_ATTR, expectedPage));

        verify(branchService,times(1)).get(branchId);
        verify(topicService,times(1)).fetchTopics(branch, 0);
    }

    @Test
    public void testShowTopicsWithPassedPage() throws Exception {
        Branch branch = new Branch("name","description");
        Page<Topic> expectedPage = new PageImpl<>(new ArrayList<Topic>());
        long branchId = 1;
        int page = 2;

        when(branchService.get(branchId)).thenReturn(branch);
        when(topicService.fetchTopics(branch,page)).thenReturn(expectedPage);

        mockMvc.perform(get("/branches/" + branchId).param("page","" + page))
                .andExpect(status().isOk())
                .andExpect(view().name(BranchController.TOPIC_LIST_VIEW))
                .andExpect(model().attribute(BranchController.BRANCH_ATTR, branch))
                .andExpect(model().attribute(BranchController.TOPICS_PAGE_ATTR, expectedPage));

        verify(branchService, times(1)).get(branchId);
        verify(topicService,times(1)).fetchTopics(branch,page);
    }


    @Test
    public void testShowTopicCreationForm() throws Exception {
        Branch branch = new Branch("name","description");
        long branchId = 1;
        when(branchService.get(branchId)).thenReturn(branch);
        mockMvc.perform(get("/branches/" + branchId + "/topic"))
                .andExpect(status().isOk())
                .andExpect(view().name(BranchController.TOPIC_FORM_VIEW))
                .andExpect(model().attribute(BranchController.BRANCH_ATTR, branch));
    }



}
