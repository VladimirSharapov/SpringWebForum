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
import org.shv.webforum.model.entity.Post;
import org.shv.webforum.service.PostService;
import org.shv.webforum.service.common.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


/**
 * @author Vladimir Sharapov
 */
@Controller
public class PostController {

    private PostService postService;

    /**
     * @param postService   service for post related operations
     */
    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    /**
     * Display a post. Calculates topic id and page number for the post
     * and redirects to corresponding page inside topic
     *
     * @param postId id of the post to show
     * @return {@code ModelAndView} with redirect to post inside topic
     */
    @RequestMapping(value = "/posts/{postId}", method = RequestMethod.GET)
    public ModelAndView navigateToPost(@PathVariable Long postId) throws NotFoundException {

        Post post = postService.get(postId);
        int pageNumber  = postService.calculatePostPage(post);

        StringBuilder sb = new StringBuilder(Redirect.TOPIC_URL).append(post.getTopic().getId())
                .append("?page=").append(pageNumber).append("#").append(post.getId());
        return new ModelAndView(sb.toString());
    }
}
