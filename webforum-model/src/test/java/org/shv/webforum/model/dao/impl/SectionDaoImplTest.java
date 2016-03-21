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
import org.shv.webforum.model.dao.SectionDao;
import org.shv.webforum.model.entity.Post;
import org.shv.webforum.model.entity.Section;
import org.shv.webforum.model.util.JpaEntityState;
import org.shv.webforum.model.util.PersistedObjectsFactory;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.apache.commons.lang.RandomStringUtils.random;

/**
 * @author Vladimir Sharapov
 */
public class SectionDaoImplTest extends  BaseDaoImplTest<Section> {

    @Autowired
    private SectionDao sectionDao;

    @Override
    protected Crud getDao() {
        return sectionDao;
    }

    @Override
    protected void changeEntity(Section section) {
        section.setName(random(Section.SECTION_NAME_MAX_LENGTH));
    }

    @Test
    public void testGetById() {
        Section section = PersistedObjectsFactory.createSection(JpaEntityState.DETACHED);
        session().get(Section.class,section.getId());
    }

    @Test
    public void testGetAll() {
        List<Post> postList1  = PersistedObjectsFactory.createPostList(10);
        List<Post> postList2  = PersistedObjectsFactory.createPostList(10);

        assertEquals(2,sectionDao.getAll().size());
    }

    //create domain objects to test constraint violation
    @Override
    protected void fillParameters() {
        entity().setName("  ");   // not blank
        entity().setName("");     // not empty
        entity().setName(null);   // not null
        entity().setName(random(Section.SECTION_NAME_MAX_LENGTH + 1));          // max length

        entity().setDescription(random(Section.SECTION_DESCRIPTION_MAX_LENGTH + 1));   //max length
    }
}
