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

import org.shv.webforum.dto.LoginUserDto;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


/**
 * Controller  for dealing with user related requests
 *
 * @author Vladimir Sharapov
 */
@Controller
public class UserController {

    private static final String LOGIN_USER_DTO = "loginUserDto";
    private static final String REFERER = "referer";

    /**
     * Display login page
     *
     * @return {@code ModelAndView} with empty data object for user credentials
     */
    @RequestMapping(value="/login", method = RequestMethod.GET)
    public ModelAndView loginPage() {

        LoginUserDto loginUserDto = new LoginUserDto();
        return new ModelAndView("login").addObject(LOGIN_USER_DTO,loginUserDto).
                                         addObject(REFERER, "http://referer.ru");

    }
}
