package org.shv.webforum.controller;

import org.shv.webforum.dto.LoginUserDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Controller class for dealing with user related requests
 *
 * @author Vladimir Sharapov
 */
@Controller
public class UserController {

    private static final String LOGIN_USER_DTO = "loginUserDto";
    private static final String REFERER = "referer";


    @RequestMapping(value="/login", method = RequestMethod.GET)
    public ModelAndView loginPage() {

        LoginUserDto loginUserDto = new LoginUserDto();
        loginUserDto.setUserName("Vladimir");
        return new ModelAndView("login").addObject(LOGIN_USER_DTO,loginUserDto).
                                         addObject(REFERER, "http://referer.ru");

    }


    /**
     * Handles login action.
     * @param loginUserDto {@link LoginUserDto} populated in form
     * @param referer    referer url
     * @param request    servlet request
     * @param response   servlet response
     * @return "success" or "fail" response status
     */
  /*  @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(LoginUserDto loginUserDto,
                              @RequestParam(REFERER) String referer,
                              @RequestParam(value = "_spring_security_remember_me", defaultValue = "off")
                              String rememberMe,
                              HttpServletRequest request, HttpServletResponse response) {


        System.out.println("username: " + loginUserDto.getUserName());
        System.out.println("password:" + loginUserDto.getPassword());
        System.out.println("referer: " + referer);
        return new ModelAndView("login");

    } */
}
