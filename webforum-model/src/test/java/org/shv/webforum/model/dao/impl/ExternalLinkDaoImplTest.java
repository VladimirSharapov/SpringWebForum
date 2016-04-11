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
package org.shv.webforum.model.dao.impl;

import org.shv.webforum.common.Crud;
import org.shv.webforum.model.util.PersistedObjectsFactory;
import org.shv.webforum.model.dao.ExternalLinkDao;
import org.shv.webforum.model.entity.ExternalLink;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.apache.commons.lang.RandomStringUtils.random;
import static org.junit.Assert.assertEquals;
import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;
import static org.shv.webforum.model.entity.ExternalLink.*;


/**
 *
 * @author Vladimir Sharapov
 */
public class ExternalLinkDaoImplTest extends BaseDaoImplTest<ExternalLink> {

    private static final int EXTERNAL_LINK_SIZE = 3;
    @Autowired
    private ExternalLinkDao dao;

    @Override
    protected Crud getDao() {
        return dao;
    }

    @Override
    protected void changeEntity(ExternalLink externalLink) {
         externalLink.setTitle(random(TITLE_MAX_SIZE));
    }

    @Test
    public void testGetAllLinks() throws Exception {
        List<ExternalLink> linkList = PersistedObjectsFactory.createExternalLinkList(EXTERNAL_LINK_SIZE);

        List<ExternalLink> actualLinkList = dao.getAll();

        assertEquals(actualLinkList.size(), EXTERNAL_LINK_SIZE);
        for(int i=0; i< actualLinkList.size(); i++) {
            assertReflectionEquals(linkList.get(i), actualLinkList.get(i));
        }
    }

//    @Test
//    public void testDIE() {
//    //    ExternalLink link = EntityFactory.getDefaultExternalLink();
//        link.setTitle(null);
//        dao.saveOrUpdate(link);
//        session().flush();
//    }

    //create domain objects to test constraint violation
    @Override
    protected void fillParameters() {
        entity().setUrl(null);            // null url
        entity().setUrl("://wf.org");     // not valid url
        entity().setUrl(HTTP_SCHEME + random(URL_MAX_SIZE - HTTP_SCHEME.length() + 1)); // too long url

        entity().setTitle(random(TITLE_MIN_SIZE-1));         // too short title
        entity().setTitle(random(TITLE_MAX_SIZE + 1));       // too long title
        entity().setTitle("");                               // empty title
        entity().setTitle(null);                             // null title

        entity().setHint(random(ExternalLink.HINT_MIN_SIZE - 1));  // too short hint
        entity().setHint(random(ExternalLink.HINT_MAX_SIZE + 1));  // too long hint
        entity().setHint(null);                                    // null hint
    }
}
