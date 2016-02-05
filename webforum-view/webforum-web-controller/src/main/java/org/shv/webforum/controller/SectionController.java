package org.shv.webforum.controller;

import org.joda.time.DateTime;
import org.shv.webforum.model.entity.Branch;
import org.shv.webforum.model.entity.Post;
import org.shv.webforum.model.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.shv.webforum.model.entity.Section;


/**
 *
 * @author Vladimir Sharapov
 */
@Controller
public class SectionController {

    @RequestMapping(value="/sections", method=RequestMethod.GET )
    public ModelAndView getSectionList() {

        return new ModelAndView("sectionList").addObject("sectionList",getTestSectionList());

    }

    private List<Section> getTestSectionList() {
        Long branchId = 0L;
        Random random = new Random();
        List<Section> resultList = new ArrayList<>();

        for(int i=0; i<5; i++) {
            Section section = new Section("sectionName" + i);
            section.setId(new Long(i));
            section.setDescription("sectionDescription" + i);
            List<Branch> branchList = new ArrayList<>();
            section.setBranches(branchList);

            for(int j=0; j < 4; j++) {
                User user = new User();
                user.setUsername("testuser" + (++branchId));

                Post post = new Post();
                post.setUserCreated(user);
                post.setCreationDate(new DateTime());

                Branch branch = new Branch("branchName" + j, "branchDescription" + j);
                branch.setId(branchId);
                branch.setLastPost(post);
                branch.setPostsCount(random.nextInt(100));
                branch.setTopicsCount(random.nextInt(100));

                branchList.add(branch);
            }

            resultList.add(section);
        }

        return resultList;
    }


}
