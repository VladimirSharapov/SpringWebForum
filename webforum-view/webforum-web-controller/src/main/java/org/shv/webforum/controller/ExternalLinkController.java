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

import org.shv.webforum.controller.util.JsonResponse;
import org.shv.webforum.controller.util.JsonResponseStatus;
import org.shv.webforum.model.entity.ExternalLink;
import org.shv.webforum.service.ExternalLinkService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * Controller for dealing with external links
 *
 * @author Vladimir Sharapov
 */
@Controller
public class ExternalLinkController {

    private ExternalLinkService service;

    /**
     * @param service  service for link CRUD operations.
     */
    @Autowired
    public ExternalLinkController(ExternalLinkService service) {
        this.service = service;
    }

    /**
     * Create new or save existing {@link ExternalLink}.
     *
     * @param link   {@link ExternalLink} for saving.
     * @param result link validation result.
     * @return response with SUCCESS status and saved link.
     */
    @RequestMapping(value = "/links", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse saveLink(@Valid @RequestBody ExternalLink link, BindingResult result) {
        if (result.hasErrors()) {
            return new JsonResponse(JsonResponseStatus.FAIL, result.getAllErrors());
        }
        service.saveLink(link);
        return new JsonResponse(JsonResponseStatus.SUCCESS, link);
    }

    /**
     * Delete {@link ExternalLink} with specified id.
     *
     * @param id link id to delete.
     * @return {@code true} if link was successfully deleted.
     */
    @RequestMapping(value = "/links/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonResponse deleteLink(@PathVariable Long id) {
        boolean result = service.deleteLink(id);
        return new JsonResponse(JsonResponseStatus.SUCCESS, result);
    }
}
