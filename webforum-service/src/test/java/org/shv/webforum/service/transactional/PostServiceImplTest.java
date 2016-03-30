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

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.shv.webforum.model.dao.PostDao;
import org.shv.webforum.model.entity.Post;
import org.shv.webforum.model.entity.Topic;
import org.shv.webforum.model.entity.User;
import org.shv.webforum.service.PostService;
import org.shv.webforum.service.UserService;
import org.shv.webforum.service.common.PAGE_SIZE;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class PostServiceImplTest {

    @Mock
    private PostDao postDao;

    @Mock
    private UserService userService;

    private PostService postService;

    @Before
    public void setUp() throws Exception {
          initMocks(this);
          postService = new PostServiceImpl(postDao,userService);
    }

    @Test
    public void testGetPostsInTopic() throws Exception {
        Topic topic = new Topic();
        int pageNumber = 5;

        Page<Post> expectedPage = new PageImpl<Post>(new ArrayList<Post>());
        when(postDao.getPostsInTopic(topic,new PageRequest(pageNumber, PAGE_SIZE.STANDARD.getSize())))
                .thenReturn(expectedPage);

        Page<Post> actualPage = postService.getPostsInTopic(topic,pageNumber);
        assertSame(expectedPage,actualPage);
    }

    @Test
    public void testAddPostToTopic() throws Exception {
        User user = new User();
        Topic topic = new Topic();
        Post  submittedPost  = new Post();

        when(userService.getCurrentUser()).thenReturn(user);

        postService.addPostToTopic(submittedPost,topic);

        assertSame(user, submittedPost.getUserCreated());
        assertSame(submittedPost,topic.getLastPost());
        assertSame(topic,submittedPost.getTopic());
    }

    @Test
    public void testCalculatePostPage() throws Exception {
        Topic topic = new Topic();
        for(int i=0; i < 3*PAGE_SIZE.STANDARD.getSize(); i++) {
            Post post = new Post();
            post.setPostContent("" + i);
            topic.addPost(post);
        }

        Post firstPost = topic.getPosts().get(0);
        Post lastPost  = topic.getLastPost();

        int firstPostPageNumber = postService.calculatePostPage(firstPost);
        int lastPostPageNumber = postService.calculatePostPage(lastPost);

        assertEquals(0,firstPostPageNumber);
        assertEquals(2,lastPostPageNumber);
    }
}