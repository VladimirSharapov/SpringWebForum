package org.shv.webforum.controller;

import junit.framework.Assert;
import org.shv.webforum.controller.util.JsonResponse;
import org.shv.webforum.controller.util.JsonResponseStatus;
import org.shv.webforum.model.entity.ExternalLink;
import org.shv.webforum.service.ExternalLinkService;

import org.springframework.validation.BindingResult;

import org.junit.Before;
import org.junit.Test;
import static junit.framework.TestCase.assertEquals;

import org.mockito.Mock;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author Vladimir Sharapov
 */
public class ExternalLinkControllerTest {

    @Mock
    private ExternalLinkService service;

    @Mock
    BindingResult bindingResult;

    private ExternalLinkController controller;

    @Before
    public void setUp() {
        initMocks(this);
        controller = new ExternalLinkController(service);
    }

    @Test
    public void savingLinkShouldPassIfNoValidationErrorsFound() throws Exception {
        when(bindingResult.hasErrors()).thenReturn(false);

        JsonResponse expected = controller.saveLink(link(), bindingResult);

        assertEquals(expected.getStatus(), JsonResponseStatus.SUCCESS);
        verify(service).saveLink(any(ExternalLink.class));
    }

    @Test
    public void testFailValidationSaveLink() throws Exception {
        when(bindingResult.hasErrors()).thenReturn(true);

        JsonResponse expected = controller.saveLink(link(), bindingResult);

        assertEquals(expected.getStatus(), JsonResponseStatus.FAIL);
        verify(service,never()).saveLink(any(ExternalLink.class));
    }

    @Test
    public void testDeleteLink() throws Exception {
        boolean expectedResult = true;
        long id = 1L;
        doReturn(expectedResult).when(service).deleteLink(eq(id));
        JsonResponse expected = controller.deleteLink(id);
        assertEquals(expected.getStatus(), JsonResponseStatus.SUCCESS);
        verify(service).deleteLink(eq(id));
    }


    private ExternalLink link() {
        ExternalLink link = new ExternalLink("url", "title", "hint");
        link.setId(1L);
        return link;
    }
}

