package org.shv.webforum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Vladimir Sharapov
 */
@Controller
public class SectionController {

    @RequestMapping(value="/section/list", method=RequestMethod.GET )
    public String getSectionList() {
        return "sectionList";
    }

}
