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

import org.shv.webforum.controller.util.Redirect;
import org.shv.webforum.model.entity.*;
import org.shv.webforum.model.validation.FormData;
import org.shv.webforum.service.BranchService;
import org.shv.webforum.service.PostService;
import org.shv.webforum.service.TopicService;
import org.shv.webforum.service.UserService;
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
 * Controller class for dealing with topic related requests
 *
 * @author Vladimir Sharapov
 */
@Controller
public class TopicController {

    private static final String POST_LIST_VIEW    = "postList";
    private static final String TOPIC_ATTR        = "topic";
    private static final String POST_ATTR         = "post";
    private static final String POST_PAGE_ATTR    = "postsPage";
    private static final String LAST_POST_ID_ATTR = "lastPostId";

    private BranchService branchService;
    private TopicService topicService;
    private PostService postService;
    private UserService userService;

    /**
     * @param branchService service for branch related operations
     * @param topicService  service for topic related operations
     * @param postService   service for post related operations
     * @param userService   service for user related operations
     */
    @Autowired
    public TopicController(BranchService branchService, TopicService topicService, PostService postService,
                           UserService userService) {
        this.branchService = branchService;
        this.topicService = topicService;
        this.postService = postService;
        this.userService = userService;
    }

    /**
     * Displays post list inside chosen topic and page number
     *
     * @param topicId id of chosen topic
     * @param page    number of page
     * @return {@code ModelAndView} with post list
     */
    @RequestMapping(value = "/topics/{topicId}", method = RequestMethod.GET)
    public ModelAndView showPosts(@PathVariable("topicId") Long topicId,
                                  @RequestParam(value="page", defaultValue = "0") int page) throws NotFoundException {

        Topic topic = topicService.get(topicId);

        Page<Post> postPage = postService.getPostsInTopic(topic,page);

        return new ModelAndView(POST_LIST_VIEW)
                .addObject(TOPIC_ATTR, topic)
                .addObject(POST_PAGE_ATTR, postPage)
                .addObject(POST_ATTR, new Post())
                .addObject(LAST_POST_ID_ATTR,getLastPostId(postPage.getContent()));
    }

    /**
     * Adds new post to topic.
     *
     * @param topicId        id of the topic to which post is added
     * @param submittedPost  post to add
     * @param result         result post data validation
     * @param page           number of the page to which we return in case of error during data validation
     * @return {@code ModelAndView} with redirect to save post or with return to referer page with errors
     */
    @RequestMapping(value = "/topics/{topicId}", method = RequestMethod.POST)
    public ModelAndView createPost(@PathVariable Long topicId,
                                   @Validated(FormData.class) Post submittedPost,
                                   BindingResult result,
                                   @RequestParam(value="page", defaultValue = "0") int page) throws NotFoundException {

        Topic topic = topicService.get(topicId);

        if(result.hasErrors()) {
            Page<Post> postPage = postService.getPostsInTopic(topic,page);

            return new ModelAndView(POST_LIST_VIEW)
                    .addObject(TOPIC_ATTR, topic)
                    .addObject(POST_PAGE_ATTR, postPage)
                    .addObject(LAST_POST_ID_ATTR,getLastPostId(postPage.getContent()));
        }

        postService.addPostToTopic(submittedPost,topic);
        int pageNumber = postService.calculatePostPage(submittedPost);
        branchService.updateLastPost(topic.getBranch(),submittedPost);
        userService.updatePostCount();
        StringBuilder sb = new StringBuilder(Redirect.TOPIC_URL).append(topicId)
                .append("?page=").append(pageNumber).append("#").append(submittedPost.getId());
        return new ModelAndView(sb.toString());
    }

    private Long getLastPostId(List<Post> list) {
        Post lastPostOnPage = list.get(list.size() - 1);
        return lastPostOnPage.getId();
    }

    /**
     * Displays all topics on a forum.
     *
     * @return {@code ModelAndView} with topic list
     */
    @RequestMapping("/topics/recent")
    public ModelAndView recentTopicsPage() {
        List<Topic> topicsList = topicService.getAllTopics();

        return new ModelAndView("topic/recent")
                .addObject("topics", topicsList);  // for rssViewer
    }
}
