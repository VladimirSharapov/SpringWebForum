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
import org.shv.webforum.model.dao.TopicDao;
import org.shv.webforum.model.entity.Branch;
import org.shv.webforum.model.entity.Post;
import org.shv.webforum.model.entity.Topic;
import org.shv.webforum.model.util.PersistedObjectsFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.apache.commons.lang.RandomStringUtils.random;
import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;


/**
 * @author Vladimir Sharapov
 */
public class TopicDaoImplTest extends BaseDaoImplTest<Topic> {

    private static final int PAGE_SIZE = 5;

    @Autowired
    private TopicDao topicDao;

    @Override
    protected Crud getDao() {
        return topicDao;
    }

    @Override
    protected void changeEntity(Topic topic) {
        topic.setTitle(random(Topic.MAX_TITLE_SIZE));
    }

    @Test
    public void testGetTopicsInBranch() {
        // create list of saved and detached topics
        // topics are ordered by creation date
        List<Topic> topicList = PersistedObjectsFactory.createTopicList(2 * PAGE_SIZE);

        Branch branch = topicList.get(0).getBranch();

        PageRequest pageRequest1 = new PageRequest(0,PAGE_SIZE);
        Page<Topic> page1 = topicDao.getTopicsInBranch(branch,pageRequest1);

        assertEquals(PAGE_SIZE, page1.getContent().size());
        assertEquals(topicList.size(),page1.getTotalElements());
        // test that fetched topics are ordered by creation date
        for(int i=0; i < PAGE_SIZE; i++) {
           assertReflectionEquals(topicList.get(i), page1.getContent().get(i));
        }

        PageRequest pageRequest2 = new PageRequest(1,PAGE_SIZE);
        Page<Topic> page2 = topicDao.getTopicsInBranch(branch,pageRequest2);

        assertEquals(PAGE_SIZE, page2.getContent().size());
        assertEquals(topicList.size(),page2.getTotalElements());
        for(int i=PAGE_SIZE; i < 2*PAGE_SIZE; i++) {
            assertReflectionEquals(topicList.get(i), page2.getContent().get(i - PAGE_SIZE));
        }
    }

    @Test
    public void testGetTopicCountInBranch() {
        // create list of saved and detached topics
        List<Topic> topicList = PersistedObjectsFactory.createTopicList(PAGE_SIZE);

        Branch branch = topicList.get(0).getBranch();

        int topicCount = topicDao.getTopicCountInBranch(branch);

        assertEquals(PAGE_SIZE,topicCount);
    }

    @Test
    public void testGetAllTopics() throws Exception {
        PersistedObjectsFactory.createTopicList(PAGE_SIZE);
        Topic topic = topicDao.getAllTopics().get(0);
        topic.getLastPost();
        //assertEquals(PAGE_SIZE,topicDao.getAllTopics().size());

    }

    //create domain objects to test constraint violation
    @Override
    protected void fillParameters() {
        entity().setTitle("  ");
        entity().setTitle("");
        entity().setTitle(null);
        entity().setTitle(random(Topic.MAX_TITLE_SIZE + 1));
        entity().setTitle(random(Topic.MIN_TITLE_SIZE - 1));

        entity().setCreationDate(null);
    }
}
