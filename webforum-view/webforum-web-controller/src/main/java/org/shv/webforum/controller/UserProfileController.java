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

import org.shv.webforum.model.entity.User;
import org.shv.webforum.model.validation.FormData;
import org.shv.webforum.service.UserService;
import org.shv.webforum.service.common.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


/**
 * Controller for dealing with user profile related requests
 *
 * @author Vladimir Sharapov
 */
@Controller
public class UserProfileController {

    private static final String USER_PROFILE_VIEW = "userProfile";
    private static final String USER_ATTR = "user";

    private  UserService userService;

    /**
     *  @param userService service for user related operations
     */
    @Autowired
    public UserProfileController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Display user profile page
     *
     * @param userId id of user
     * @return {@code ModelAndView} with user profile data
     */
    @RequestMapping(value="/users/{userId}", method = RequestMethod.GET)
    public ModelAndView getUserProfile(@PathVariable Long userId) throws NotFoundException {
        User user = userService.get(userId);

        return new ModelAndView(USER_PROFILE_VIEW).addObject(USER_ATTR,user);
    }

    /**
     * Update user profile data
     *
     * @param user   new user data
     * @param result validation result
     * @return {@code ModelAndView} with redirect to user profile or with return to user profile page with validation errors
     */
    @RequestMapping(value="/users/*", method = RequestMethod.POST)
    public ModelAndView saveUserProfile(@Validated(FormData.class) User user, BindingResult result) {
        if(result.hasErrors()) {
            return new ModelAndView(USER_PROFILE_VIEW).addObject(USER_ATTR,user);
        }

        userService.editUser(user);

        return new ModelAndView("redirect:/users/" + user.getId());
    }
}
