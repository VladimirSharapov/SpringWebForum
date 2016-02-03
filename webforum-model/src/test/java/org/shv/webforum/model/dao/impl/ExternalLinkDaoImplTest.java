package org.shv.webforum.model.dao.impl;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;

import org.shv.webforum.model.dao.ExternalLinkDao;
import org.shv.webforum.model.entity.ExternalLink;
import org.shv.webforum.model.util.EntityFactory;

import javax.validation.ConstraintViolationException;

/**
 *
 * @author Vladimir Sharapov
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/applicationContext-dao.xml")
@Transactional
public class ExternalLinkHibernateDaoTest {
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private ExternalLinkDao dao;

    private Session session;

    @Before
    public void setUp() throws Exception {
        session = sessionFactory.getCurrentSession();
    }

    @Test
    public void testSave() throws Exception {
        long id = 1L;
        ExternalLink expected = EntityFactory.getDefaultExternalLink();
        expected.setId(id);
        session.save(expected);
        session.flush();
        session.clear();

        ExternalLink actual = (ExternalLink) session.get(ExternalLink.class, expected.getId());
        assertReflectionEquals(expected, actual);
    }

    @Test
    public void testGetMissingId() {
        assertNull(dao.get(Long.MAX_VALUE));
    }

    @Test
    public void testUpdate() throws Exception {
        ExternalLink link = EntityFactory.getDefaultExternalLink();
        dao.saveOrUpdate(link);
        session.flush();
        session.clear();

        link = (ExternalLink) session.get(ExternalLink.class, link.getId());
        fillFieldsRandomly(link);

        dao.saveOrUpdate(link);
        session.flush();
        session.clear();

        ExternalLink actual = (ExternalLink) session.get(ExternalLink.class, link.getId());
        assertReflectionEquals(link, actual);
    }

    @Test
    public void testGetLinks() throws Exception {
        List<ExternalLink> link = EntityFactory.getExternalLinks(3);
        for (ExternalLink externalLink : link) {
            session.saveOrUpdate(externalLink);
        }
        session.clear();

        List<ExternalLink> actual = dao.getAll();
        for(int i=0; i< actual.size(); i++) {
            assertReflectionEquals(link.get(i), actual.get(i));
        }
        assertEquals(actual.size(), 3);
    }

    @Test(expected = ConstraintViolationException.class)
    public void shouldFailWithEmptyTitle() {
        ExternalLink link = EntityFactory.getDefaultExternalLink();
        link.setTitle("");
        dao.saveOrUpdate(link);
    }

    @Test(expected = ConstraintViolationException.class)
    public void shouldFailWithNullTitle() {
        ExternalLink link = EntityFactory.getDefaultExternalLink();
        link.setTitle(null);
        dao.saveOrUpdate(link);
    }


    @Test(expected = ConstraintViolationException.class)
    public void shouldFailWithLongTitle() {
        ExternalLink link = EntityFactory.getDefaultExternalLink();
        link.setTitle(RandomStringUtils.random(ExternalLink.TITLE_MAX_SIZE + 1, true, false));
        dao.saveOrUpdate(link);
    }

    @Test
    public void shouldSuccessWithMaxLengthTitle() {
        ExternalLink link = EntityFactory.getDefaultExternalLink();
        link.setTitle(RandomStringUtils.random(ExternalLink.TITLE_MAX_SIZE, true, false));
        dao.saveOrUpdate(link);
    }

    @Test
    public void shouldSuccessWithMinLengthTitle() {
        ExternalLink link = EntityFactory.getDefaultExternalLink();
        link.setTitle(RandomStringUtils.random(ExternalLink.TITLE_MIN_SIZE, true, false));
        dao.saveOrUpdate(link);
    }

    @Test(expected = ConstraintViolationException.class)
    public void shouldFailWithNullUrl() {
        ExternalLink link = EntityFactory.getDefaultExternalLink();
        link.setUrl(null);
        dao.saveOrUpdate(link);
    }

    @Test(expected = ConstraintViolationException.class)
    public void shouldFailWithNotValidUrl() {
        ExternalLink link = EntityFactory.getDefaultExternalLink();
        link.setUrl("://wf.org");
        dao.saveOrUpdate(link);
    }

    @Test(expected = ConstraintViolationException.class)
    public void shouldFailWithLongUrl() {
        ExternalLink link = EntityFactory.getDefaultExternalLink();
        //-10 = protocol + domen
        link.setUrl("http://" + RandomStringUtils.random(ExternalLink.URL_MAX_SIZE - 10, true, false) + ".org");
        session.saveOrUpdate(link);
    }

    @Test
    public void shouldSuccessWithMaxLengthUrl() {
        ExternalLink link = EntityFactory.getDefaultExternalLink();
        //-10 = protocol + domen
        link.setUrl("http://" + RandomStringUtils.random(ExternalLink.URL_MAX_SIZE - 11, true, false) + ".org");
        dao.saveOrUpdate(link);
    }

    @Test(expected = ConstraintViolationException.class)
    public void nullHintForExternalLinkShouldRaiseConstraintException() {
        ExternalLink link = EntityFactory.getDefaultExternalLink();
        link.setHint(null);
        dao.saveOrUpdate(link);
    }

    @Test
    public void shouldSuccessWithMaxLengthHint() {
        ExternalLink link = EntityFactory.getDefaultExternalLink();
        link.setHint(RandomStringUtils.random(ExternalLink.HINT_MAX_SIZE, true, false));
        dao.saveOrUpdate(link);
    }

    @Test(expected = ConstraintViolationException.class)
    public void shouldFailWithLongHint() {
        ExternalLink link = EntityFactory.getDefaultExternalLink();
        link.setHint(RandomStringUtils.random(ExternalLink.HINT_MAX_SIZE + 1, true, false));
        dao.saveOrUpdate(link);
    }

    private void fillFieldsRandomly(ExternalLink link) {
        link.setTitle("New title");
        link.setUrl(StringEscapeUtils.escapeJava("http://wf.org"));
        link.setHint("New hint");
    }
}
