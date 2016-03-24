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

import org.shv.webforum.controller.util.ForumStatisticsProvider;
import org.shv.webforum.service.SectionService;
import org.shv.webforum.service.common.NotFoundException;
import org.shv.webforum.model.entity.Section;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;


/**
 * Controller class for dealing with section related requests
 *
 * @author Vladimir Sharapov
 */
@Controller
public class SectionController {

    private static Logger logger =  LoggerFactory.getLogger(SectionController.class);

    private static final String SECTION_LIST_VIEW     = "sectionList";
    private static final String SHOW_FORUM_STATS_ATTR = "showForumStatus";
    private static final String SECTION_LIST_ATTR     = "sectionList";

    private ForumStatisticsProvider forumStatisticsProvider;
    private SectionService sectionService;

    /**
     * @param sectionService            for all operations with sections
     * @param forumStatisticsProvider   provides forum statistics, such as number of registered users, guests and so on
     */
    @Autowired
    public SectionController(SectionService sectionService,ForumStatisticsProvider forumStatisticsProvider) {
        this.sectionService = sectionService;
        this.forumStatisticsProvider = forumStatisticsProvider;
    }

    /**
     * Display list of forum sections
     * @return {@code ModelAndView} with list of sections
     */
    @RequestMapping(value={"","/sections"}, method=RequestMethod.GET )
    public ModelAndView showSections() {
        List<Section> sectionList = sectionService.getAll();
        sectionService.prepareSectionsForView(sectionList);

        return new ModelAndView(SECTION_LIST_VIEW)
                .addObject(SECTION_LIST_ATTR, sectionList)
                .addObject(SHOW_FORUM_STATS_ATTR,true)
                .addObject("postsCount", forumStatisticsProvider.getAllPostsCount())
                .addObject("usersCount",forumStatisticsProvider.getAllUsersCount())
                .addObject("onlineUsersCount",forumStatisticsProvider.getOnlineUsersCount())
                .addObject("registeredUsersCount",forumStatisticsProvider.getOnlineRegisteredUsersCount())
                .addObject("anonymousCount", forumStatisticsProvider.getOnlineAnonymousUsersCount());
    }

    /**
     * Display chosen section
     * @param sectionId   id of section to display
     * @return {@code ModelAndView} with a single section
     */
    @RequestMapping(value="/sections/{sectionId}", method=RequestMethod.GET )
    public ModelAndView showSection(@PathVariable Long sectionId) throws NotFoundException {
        List<Section> sectionList = new ArrayList<>();

        Section section = sectionService.get(sectionId);
        sectionList.add(section);
        sectionService.prepareSectionsForView(sectionList);

        return new ModelAndView(SECTION_LIST_VIEW)
                .addObject(SECTION_LIST_ATTR, sectionList)
                .addObject(SHOW_FORUM_STATS_ATTR,false);
    }
}
