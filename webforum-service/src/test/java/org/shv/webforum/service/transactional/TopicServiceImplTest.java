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

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.shv.webforum.model.dao.PostDao;
import org.shv.webforum.model.dao.TopicDao;
import org.shv.webforum.model.entity.Branch;
import org.shv.webforum.model.entity.Post;
import org.shv.webforum.model.entity.Topic;
import org.shv.webforum.model.entity.User;
import org.shv.webforum.service.TopicService;
import org.shv.webforum.service.UserService;
import org.shv.webforum.service.common.PAGE_SIZE;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static junit.framework.Assert.assertEquals;

/**
 * @author Vladimir Sharapov
 */
public class TopicServiceImplTest {

    @Mock
    private TopicDao topicDao;

    @Mock
    private PostDao postDao;

    @Mock
    UserService userService;

    private TopicService topicService;

    @Before
    public void setUp() {
        initMocks(this);
        topicService = new TopicServiceImpl(topicDao,postDao,userService);
    }

    @Test
    public void testFetchTopics() {
        Branch branch = new Branch("","");
        PageRequest pageRequest = new PageRequest(10, PAGE_SIZE.STANDARD.getSize());
        Page<Topic> expectedPage = new PageImpl<>(new ArrayList<Topic>(),pageRequest,50);
        when(topicDao.getTopicsInBranch(branch,pageRequest)).thenReturn(expectedPage);

        Page<Topic> actualPage = topicService.fetchTopics(branch,10);

        verify(topicDao).getTopicsInBranch(branch,pageRequest);
        assertEquals(expectedPage, actualPage);
    }

    @Test
    public void testCreateTopicWithPost() {
        User user = new User();
        when(userService.getCurrentUser()).thenReturn(user);

        Branch branch = new Branch("","");
        Topic topic = new Topic();
        Post post   = new Post();

        Topic createdTopic = topicService.createTopicWithPost(branch,topic,post);

        verify(userService).getCurrentUser();
        verify(topicDao).saveOrUpdate(topic);
        verify(postDao).saveOrUpdate(post);

        assertSame(branch, topic.getBranch());
        assertSame(topic,post.getTopic());
        assertSame(user, createdTopic.getTopicStarter());
        assertSame(user, post.getUserCreated());
    }



}
