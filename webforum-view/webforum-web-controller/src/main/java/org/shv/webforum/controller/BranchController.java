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

import org.shv.webforum.controller.dto.TopicPostDto;
import org.shv.webforum.controller.util.Redirect;
import org.shv.webforum.model.entity.*;
import org.shv.webforum.model.validation.FormData;
import org.shv.webforum.service.BranchService;
import org.shv.webforum.service.TopicService;
import org.shv.webforum.service.common.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


/**
 * Controller for dealing with branch related requests
 *
 * @author Vladimir Sharapov
 */
@Controller
public class BranchController {

    public  static final String TOPIC_LIST_VIEW = "topicList";
    public  static final String TOPIC_FORM_VIEW = "topicForm";
    public  static final String SECTION_ATTR = "section";
    public  static final String BRANCH_ATTR  = "branch";
    private static final String URL_SUFFIX = "/branches/";
    public static final String TOPICS_ATTR = "topics";
    public static final String TOPICS_PAGE_ATTR = "topicsPage";

    private TopicService topicService;
    private BranchService branchService;

    /**
     * @param topicService  service for managing topic related operations
     * @param branchService service for managing branch related operations
     */
    @Autowired
    public BranchController(TopicService topicService, BranchService branchService) {
        this.topicService = topicService;
        this.branchService = branchService;
    }

    /**
     * Displays list of topics inside a chosen branch and page
     *
     * @param branchId  id of branch that contains displayed topics
     * @param page      page number to display
     * @return {@code ModelAndView} with topic list
     * @throws NotFoundException
     */
    @RequestMapping(value="/branches/{branchId}", method = RequestMethod.GET)
    public ModelAndView showTopics(@PathVariable Long branchId,
                                   @RequestParam(value="page", defaultValue = "0") int page) throws NotFoundException {

        Branch branch = branchService.get(branchId);

        Page<Topic> topicPage = topicService.fetchTopics(branch,page);

        return new ModelAndView(TOPIC_LIST_VIEW)
                .addObject(SECTION_ATTR, branch.getSection())
                .addObject(BRANCH_ATTR,branch)
                .addObject(TOPICS_ATTR, topicPage.getContent())
                .addObject(TOPICS_PAGE_ATTR, topicPage);
    }

    /**
     * Show topic creation form. Topic is created with the first post inside it simultaneously.
     * That is topic always contains at least one post.
     *
     * @param branchId      id of the branch inside which topic will be created
     * @param topicPostDto  data transfer object which contains topic title and post message
     * @return {@code ModelAndView} with branch and section entity
     */
    @RequestMapping(value = "/branches/{branchId}/topic", method = RequestMethod.GET)
    public ModelAndView showTopicCreationForm(@PathVariable Long branchId, @ModelAttribute TopicPostDto topicPostDto) throws NotFoundException {

        Branch branch = branchService.get(branchId);

        return new ModelAndView(TOPIC_FORM_VIEW)
                .addObject(BRANCH_ATTR, branch)
                .addObject(SECTION_ATTR, branch.getSection());
    }

    /**
     *  Creates topic with the first post inside this topic.
     *
     * @param topicPostDto data transfer object which contains topic title and post message
     * @param result       result of data validation
     * @param branchId     id of branch inside which topic will be created
     * @return {@code ModelAndView} with branch and section entity
     */
    @RequestMapping(value = "/branches/{branchId}/topic", method = RequestMethod.POST)
    public ModelAndView createTopicWithPost(@Validated(FormData.class) TopicPostDto topicPostDto, BindingResult result, @PathVariable Long branchId) throws NotFoundException {
        Branch branch = branchService.get(branchId);

        if(result.hasErrors()) {
            return new ModelAndView(TOPIC_FORM_VIEW)
                    .addObject(BRANCH_ATTR, branch)
                    .addObject(SECTION_ATTR, branch.getSection());
        }

        Topic createdTopic = topicService.createTopicWithPost(branch,topicPostDto.getTopic(),topicPostDto.getMessage());

        return new ModelAndView(Redirect.TOPIC_URL + createdTopic.getId());
    }

    /**
     * Displays all posts for the branch.
     * @param branchId  id of the branch for which to display posts
     * @return {@code ModelAndView} with post list
     */
    @RequestMapping("/branches/{branchId}/recent")
    public ModelAndView recentBranchPostsPage(@PathVariable("branchId") long branchId) throws NotFoundException {
        Branch branch = branchService.get(branchId);

        List<Post> posts = branchService.getAllPostsInBranch(branch);

        return new ModelAndView("posts/recent")
                .addObject("feedTitle", branch.getName())
                .addObject("feedDescription", branch.getDescription())
                .addObject("urlSuffix",URL_SUFFIX + branch.getId())
                .addObject("posts", posts);
    }
}
