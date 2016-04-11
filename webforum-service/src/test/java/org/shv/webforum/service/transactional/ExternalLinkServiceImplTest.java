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
package org.shv.webforum.service.transactional;

import org.shv.webforum.model.dao.ExternalLinkDao;
import org.shv.webforum.model.entity.ExternalLink;
import org.shv.webforum.service.ExternalLinkService;

import org.junit.Before;
import org.junit.Test;

import org.mockito.Mock;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;


/**
 * @author Vladimir Sharapov
 */
public class ExternalLinkServiceImplTest {

    @Mock
    private ExternalLinkDao dao;
    private ExternalLinkService service;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        service = new ExternalLinkServiceImpl(dao);
    }

    @Test
    public void testGetLinks() throws Exception {
        service.getLinks();
        verify(dao).getAll();
    }

    @Test
    public void testAddLink() throws Exception {
        ExternalLink linkToSave = new ExternalLink();
        service.saveLink(linkToSave);
        verify(dao).saveOrUpdate(linkToSave);
    }

    @Test
    public void testRemoveLink() throws Exception {
        service.deleteLink(1L);
        verify(dao).delete(eq(1L));
    }
}
